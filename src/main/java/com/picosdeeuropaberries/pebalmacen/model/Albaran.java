package com.picosdeeuropaberries.pebalmacen.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Albaran {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreationTimestamp
	private Timestamp fechaCreacion;

	String codigo;
	
	private String observaciones;

	@OneToOne
	private Envio envio;
	
}
