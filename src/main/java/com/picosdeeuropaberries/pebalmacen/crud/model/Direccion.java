package com.picosdeeuropaberries.pebalmacen.crud.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Direccion {

	private Integer id;
	private Timestamp fecha_creacion;
	private String tipo_direccion;
	private Boolean activo;
	private String nombre;
	private String direccion;
	private String cp;
	private Integer provincia_id;
	private String observaciones;
	
}
