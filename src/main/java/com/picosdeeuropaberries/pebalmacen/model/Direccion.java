package com.picosdeeuropaberries.pebalmacen.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreationTimestamp
	private Timestamp fechaCreacion;

	@Enumerated(EnumType.STRING)
	private TipoDireccion tipoDireccion;

	@NotNull
	private Boolean activo;

	@NotNull
	private String nombre;
	
	private String direccion;
	
	private String cp;
	
	@ManyToOne
	private Provincia provincia;

	private String observaciones;
	
}
