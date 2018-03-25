package com.picosdeeuropaberries.pebalmacen.model;

import java.sql.Timestamp;
import java.util.Date;
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
public class Envio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreationTimestamp
	private Timestamp fechaCreacion;

	@ManyToOne
	private Cliente cliente;
	
	private String observaciones;

	@ManyToOne
	private Pedido pedido;
	
	@Enumerated(EnumType.STRING)
	private TipoEcologico tipoEcologico;
	
	@Enumerated(EnumType.STRING)
	private EstadoEnvio estado;
	
	@ManyToOne
	private Contacto contacto;
	
	@ManyToOne
	private Direccion direccionEnvio;
	
	private Date fechaHoraSolicitud;
	
	private Date fechaHoraPreparado;
	
	private Date fechaHoraEnviado;
	
	@OneToMany(mappedBy = "envio")
	private Set<Palet> palets;
	
}
