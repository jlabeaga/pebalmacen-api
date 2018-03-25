package com.picosdeeuropaberries.pebalmacen.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class EntradaProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreationTimestamp
	private Timestamp fechaCreacion;

	@ManyToOne
	private Entrada entrada;

	@ManyToOne
	private Lote lote;
	
	private int pesoBruto;

	private int pesoNeto;
	
}
