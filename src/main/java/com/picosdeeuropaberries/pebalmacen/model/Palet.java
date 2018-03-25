package com.picosdeeuropaberries.pebalmacen.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Palet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreationTimestamp
	private Timestamp fechaCreacion;

	private String codigo;
	
	@Enumerated(EnumType.STRING)
	private EstadoPalet estado;
	
	@Enumerated(EnumType.STRING)
	private TipoEcologico tipoEcologico;
	
	@OneToMany(mappedBy="palet")
	private Set<Partida> partidas;
	
	@ManyToOne
	private Envio envio;
	
}
