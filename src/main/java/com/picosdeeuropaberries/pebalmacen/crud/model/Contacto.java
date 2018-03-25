package com.picosdeeuropaberries.pebalmacen.crud.model;

import java.sql.Timestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.picosdeeuropaberries.pebalmacen.model.TipoContacto;

import lombok.Data;

@Data
public class Contacto {

	private Integer id;
	private Timestamp fecha_creacion;
	private String tipo_contacto;
	private Boolean activo;
	private String nombre;
	private String telefono;
	private String email;
	private String observaciones;
	
}
