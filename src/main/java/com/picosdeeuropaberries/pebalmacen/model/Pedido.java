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
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@CreationTimestamp
	private Timestamp fechaCreacion;

	@ManyToOne
	private Cliente cliente;
	
	private String observaciones;

	@Enumerated(EnumType.STRING)
	private EstadoPedido estado;
	
	@ManyToOne
	private Contacto contacto;
	
	@ManyToOne
	private Direccion direccionEnvio;
	
	private Date fechaHoraSolicitud;
	
	private Date fechaHoraPreparado;
	
	private Date fechaHoraEnviado;
	
}
