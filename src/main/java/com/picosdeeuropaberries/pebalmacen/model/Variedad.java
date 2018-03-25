package com.picosdeeuropaberries.pebalmacen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Variedad {

	@Id
	private Integer id;
	
	@Column(nullable = false, updatable = false)
	private String codigo;
	
	@NotNull
	private String nombre;
	
	
}
