package com.picosdeeuropaberries.pebalmacen.crud.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Cliente {

	private Integer id;

	private Timestamp fecha_creacion;

	private Boolean activo;

	private String nombre;
	
	private String cif;
	
	private String observaciones;
	
	private Integer contacto_id;

	private Integer direccion_facturacion;

	private Integer direccion_envio;

}
