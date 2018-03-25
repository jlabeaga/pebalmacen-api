package com.picosdeeuropaberries.pebalmacen.model;

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
@Entity
public class Transportista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreationTimestamp
	private Timestamp fechaCreacion;

	@NotNull
	private Boolean activo;

	@NotNull
	private String nombre;
	
	private String cif;
	
	private String observaciones;
	
	@OneToOne
	private Contacto contacto;

	@OneToOne
	private Direccion direccionFacturacion;

	@OneToOne
	private Direccion direccionEnvio;

}
