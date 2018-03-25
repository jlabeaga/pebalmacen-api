package com.picosdeeuropaberries.pebalmacen.crud;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.picosdeeuropaberries.pebalmacen.crud.model.Test;

@Component
public class DataMapper {

	Map<String, Class> table2class = new HashMap<String, Class>();
	Map<Class, String> class2table = new HashMap<Class, String>();
	
	public DataMapper() {
		add("test", Test.class);
	}
	
	public void add(String tableName, Class clazz) {
		table2class.put(tableName, clazz);
		class2table.put(clazz, tableName);
	}
	
	public Class getClass(String tableName) {
		return table2class.get(tableName);
	}
	
	public String getTablename(Class clazz) {
		return class2table.get(clazz);
	}
	
}
