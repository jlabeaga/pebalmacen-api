package com.picosdeeuropaberries.pebalmacen.crud.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
public class Socio {

	private Integer id;

	private Timestamp fecha_creacion;

	private Boolean activo;

	private String codigo;

	private String nombre;
	
	private String cif;
	
	private String observaciones;
	
	private Integer contacto_id;

	private Integer direccion_facturacion;

	private Integer direccion_envio;

}
