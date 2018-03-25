package com.picosdeeuropaberries.pebalmacen.crud;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.picosdeeuropaberries.pebalmacen.exception.CodigosError;
import com.picosdeeuropaberries.pebalmacen.exception.ErrorUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CrudDAO  {
	
	@Value("${db.rollback.all:false}")
	protected Boolean rollbackAll;

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private DataMapper dataMapper;
	
	private DateFormat defaultDateFormat = new ISO8601DateFormat();
	
	private ImprovedNamingStrategy namingStrategy = new ImprovedNamingStrategy();
	
	public Iterable<Object> findAll(String tableName) {
		String sql = "select * from " + tableName + ";";
		Iterable<Object> result  = jdbcTemplate.query(sql, new BeanPropertyRowMapper(dataMapper.getClass(tableName)));
		return result;
	}
	
	public Object findOne(String tableName, Integer id)  { 
		String sql = "select * from " + tableName + " where id = :id ;"; 
		
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		Object result = null;
		try {
			result = jdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper(dataMapper.getClass(tableName)));
			log.trace("result = " + result);
		} catch(EmptyResultDataAccessException e) {
			throw ErrorUtils.logAndBuildException(
					CodigosError.GENERAL_DATO_NO_ENCONTRADO,
					"Elemento no encontrado: id = " + id + " , SQL = "  + sql,
					e);
		}

		return result;
	}
	
	public Object insert(String tableName, Object entity) {
		return insert(tableName, entity, true);
	}
	
	public Object insert(String tableName, Object entity, boolean isAutogeneratedId) {
		List<String> excludedFields = new ArrayList<String>();
		if( isAutogeneratedId ) {
			excludedFields.add("id");
		}

		String sql = "insert into " + tableName +
				" (" + getFieldNamesComma(dataMapper.getClass(tableName), excludedFields) + ")" +
				" values (" + getFieldNamesColon(dataMapper.getClass(tableName), excludedFields) + ") ";		
	
		log.trace("insert entity = " + entity);
		log.trace("sql = " + sql);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource namedParameters;
		int numAffectedRows = 0;
		try {
			namedParameters = addValuesToParameterSource(dataMapper.getClass(tableName), entity, excludedFields, true);
		} catch (IllegalArgumentException | IllegalAccessException | JsonProcessingException e) {
			throw ErrorUtils.logAndBuildException(
					CodigosError.GENERAL_ERROR_TECNICO,
					"Error de Java Reflection al acceder a propiedad en " + this.getClass().getSimpleName() + " para " + dataMapper.getClass(tableName).getSimpleName(),
					e);
		} 
	
		try {
			numAffectedRows = jdbcTemplate.update(sql, namedParameters, keyHolder);
		} catch (DataIntegrityViolationException e) {
			throw ErrorUtils.logAndBuildException(
					CodigosError.GENERAL_ERROR_INTEGRIDAD_DATOS,
					"Error al intentar insertar elemento " + entity + " , SQL = "  + sql,
					e);
		} catch (Exception e) {
			throw ErrorUtils.logAndBuildException(
					CodigosError.GENERAL_ERROR_TECNICO,
					"Error al intentar insertar elemento " + entity + " , SQL = "  + sql,
					e);
		}
		
		HashMap<String, Object> pk = new HashMap<>();
		
		if(rollbackAll){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return findOne(tableName, (Integer)keyHolder.getKeys().get("id"));
	}
	
	
	public Object update(String tableName, Object entity) {
		return update(tableName, entity, null);
	}
	
	public Object update(String tableName, Object entity, List<String> excludedFields) {
				
		String sql = "update " + tableName + 
					" set " + getFieldNamesSet(dataMapper.getClass(tableName), excludedFields) +
					" where id = :id ;";
		
		log.trace("update entity = " + entity);
		log.trace("sql = " + sql);

		SqlParameterSource namedParameters = null;
		int numAffectedRows = 0;
		try {
			namedParameters = addValuesToParameterSource(dataMapper.getClass(tableName), entity, excludedFields, true);
			numAffectedRows = jdbcTemplate.update(sql, namedParameters);
		} catch (DataIntegrityViolationException e) {
			throw ErrorUtils.logAndBuildException(
					CodigosError.GENERAL_ERROR_INTEGRIDAD_DATOS,
					"Error al intentar modificar elemento: " + entity + " , SQL = "  + sql,
					e);
		} catch (Exception e) {
			throw ErrorUtils.logAndBuildException(
					CodigosError.GENERAL_ERROR_TECNICO,
					"Error al intentar modificar elemento: " + entity + " , SQL = "  + sql,
					e);
		}
		if(numAffectedRows < 1){
			throw ErrorUtils.logAndBuildException(CodigosError.GENERAL_DATO_NO_ENCONTRADO, "Elemento no encontrado: " + entity + " , SQL = " + sql); 

		}
		
		if(rollbackAll){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return entity;
	}
	
	public void delete(String tableName, Integer id) {
		String sql = "delete from " + tableName + " where id = :id ; ";
		log.trace("delete id = " + id);
		log.trace("sql = " + sql);
		
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		int numAffectedRows = 0;
		try {
			numAffectedRows = jdbcTemplate.update(sql, namedParameters);
		} catch (Exception e) {
			throw ErrorUtils.logAndBuildException(
					CodigosError.GENERAL_ERROR_TECNICO,
					"Error al intentar eliminar elemento: id = " + id + " , SQL = "  + sql,
					e);
		}
		if(numAffectedRows < 1){
			throw ErrorUtils.logAndBuildException(CodigosError.GENERAL_DATO_NO_ENCONTRADO, "Elemento no encontrado: id = " + id + " , SQL = " + sql); 

		}
		
		if(rollbackAll){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		log.debug("num elementos afectados = " + numAffectedRows);
	}
	

	private List<Field> getFields(Class entityClass) {
		return Arrays.asList(entityClass.getDeclaredFields()).stream().filter(field -> !field.getName().equals("serialVersionUID")).collect(Collectors.toList());
	}
	
	private List<String> getFieldNames(Class entityClass, List<String> excludedFields) {
		return getFields(entityClass).stream()
				.map(field -> field.getName())
				.filter(name -> ( excludedFields == null || !excludedFields.contains(name)))
				.collect(Collectors.toList());
	}
	
	private String getFieldNamesComma(Class entityClass, List<String> excludedFields) {
		List<String> fieldNames = getFieldNames(entityClass, excludedFields);

		fieldNames = fieldNames
						.stream()
						.map(fieldName -> namingStrategy.columnName(fieldName))
						.collect(Collectors.toList());
		
		return String.join(",",fieldNames);
	}
	
	private String getFieldNamesColon(Class entityClass, List<String> excludedFields) {
		List<String> fieldNames = getFieldNames(entityClass, excludedFields);

		return String.join(",", 
				fieldNames
				.stream()
				.map(fieldName -> { return ":"+fieldName; })
				.collect(Collectors.toList())
		);
	}

	private String getFieldNamesWhereAnd(Class entityClass, List<String> includeFields) {
		return String.join(" AND ",
				getFieldNames(entityClass, null)
				.stream()
				.filter(name -> includeFields.contains(name))
				.map(fieldName -> {	return namingStrategy.columnName(fieldName) + " = :"+fieldName; })
				.collect(Collectors.toList())
		);
	}
	
	private String getFieldNamesSet(Class entityClass, List<String> excludedFields) {
		List<String> fieldNames =
			getFieldNames(entityClass, excludedFields)
			.stream()
			.map(fieldName -> {	return namingStrategy.columnName(fieldName) + " = " + ":"+fieldName; })
			.collect(Collectors.toList());
		return String.join(",", fieldNames);
	}
	
	private SqlParameterSource addValuesToParameterSource(Class entityClass, Object entity, List<String> columns, boolean exclude) throws IllegalArgumentException, IllegalAccessException, JsonProcessingException {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		for( Field field : getFields(entityClass) ) {
			//if( !field.getName().equalsIgnoreCase("id") && !field.getName().equalsIgnoreCase("serialVersionUID") ) {
			if(columns == null || !columns.contains(field.getName())){
				String fieldName = field.getName();
				Object fieldValue = null;
				if( java.util.Map.class.isInstance(entity) ) {
					fieldValue = getCastedValue( ((Map)entity).get(fieldName), field.getType() );
				} else {
					field.setAccessible(true);
					fieldValue = field.get(entity);
				}
				namedParameters.addValue(fieldName, fieldValue);
				log.trace("añadido campo: " + fieldName + " = " + fieldValue);
			}
		}
		return namedParameters;
	}
	
	private Object getCastedValue(Object value, Class targetType) {
		Object result = null;
		if( value == null ) return null;
		try  {
			log.trace("Itentando convertir el valor " + value.toString() + " desde '" + value.getClass() + "' a '" + targetType.getName() + "'");
			if( targetType == java.sql.Timestamp.class ) {
				result = new java.sql.Timestamp(defaultDateFormat.parse(value.toString()).getTime());
				log.trace("Valor convertido = " + result);
			} else if( targetType == java.util.Date.class ) {
				result = defaultDateFormat.parse(value.toString());
				log.trace("Valor convertido = " + result);
			} else {
				result = value;
				log.trace("Valor NO convertido = " + result);
			}
			log.trace("Valor resultante = " + result);
			return result;
		} catch( Exception e ) {
			ErrorUtils.logAndBuildException(
				CodigosError.GENERAL_ERROR_TECNICO, 
				"Error al intentar convertir el valor " + value.toString() + " desde '" + value.getClass() + "' a '" + targetType.getName() + "'", 
				e);
		}
		return result;
	}
	
	// De momento invocamos las operaciones CRUD multiples de manera secuencial
	// Estudiaremos la posibilidad de invocar las operaciones en modo batch
	// http://www.logicbig.com/tutorials/spring-framework/spring-data-access-with-jdbc/spring-jdbc-batch-update/

	public List<Object> insertMultiple(String tableName, List<Object> entities) {
		List<Object> result = new ArrayList<Object>();
		for( Object entity : entities ) {
			entity = insert(tableName, entity);
			result.add(entity);
		}
		
		if(rollbackAll){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	public List<Object> updateMultiple(String tableName, List<Object> entities) {
		List<Object> result = new ArrayList<Object>();
		for( Object entity : entities ) {
			entity = update(tableName, entity);
			result.add(entity);
		}
		
		if(rollbackAll){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	public void deleteMultiple(String tableName, List<Integer> ids) {
		for( Integer id : ids ) {
			delete(tableName, id);
		}
		
		if(rollbackAll){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}	
}