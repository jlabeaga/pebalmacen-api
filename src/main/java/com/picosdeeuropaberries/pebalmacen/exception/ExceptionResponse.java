package com.picosdeeuropaberries.pebalmacen.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
	
	private String codigo;
	private String descripcion;
	private String ticket;
	//@JsonIgnore
	private String message;
	//@JsonIgnore
	private String causeMessage;
	@JsonIgnore
	private Throwable cause;
	
}
