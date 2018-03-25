package com.picosdeeuropaberries.pebalmacen.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreationTimestamp
	private Timestamp fechaCreacion;

	private int codigo;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Lote lote;

	private int peso;
	
	@Enumerated(EnumType.STRING)
	private EstadoPartida estadoPartida;
	
	@ManyToOne
	private Palet palet;
	
}
