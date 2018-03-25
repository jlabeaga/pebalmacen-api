package com.picosdeeuropaberries.pebalmacen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picosdeeuropaberries.pebalmacen.crud.CrudDAO;

@Service
public class CrudService {

	@Autowired
	CrudDAO crudDAO;
	
	public Iterable<Object> findAll(String entityName) {
		return crudDAO.findAll(entityName);
	};
	
	public Object findOne(String entityName, Integer id) {
		return crudDAO.findOne(entityName, id);
	};
	
	public Object insert(String entityName, Object entity) {
		return crudDAO.insert(entityName, entity);
	};
	
	public Object update(String entityName, Integer id, Object entity) {
		return crudDAO.update(entityName, entity);
	};
	
	public void delete(String entityName, Integer id) {
		crudDAO.delete(entityName, id);
	}
	
}
