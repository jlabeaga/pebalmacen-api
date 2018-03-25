package com.picosdeeuropaberries.pebalmacen.model;

import java.sql.Timestamp;
import java.util.Date;

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
public class HistoricoPartida {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreationTimestamp
	private Timestamp fechaCreacion;

	@ManyToOne
	private Partida partida;
	
	private Date fecha;
	
	@Enumerated(EnumType.STRING)
	private EstadoPartida estadoPartida;

}
