package com.picosdeeuropaberries.pebalmacen.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PebalmacenException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private CodigosError codigo;
	private Throwable cause;
	private String message;
	private String ticket = "PEBALMACEN-TICKET-" + new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-SSS").format(new Date()); 
	
	public PebalmacenException(CodigosError codigo) {
		this.codigo = codigo;
	}
	
	public PebalmacenException(CodigosError codigo, Throwable cause) {
		this.codigo = codigo;
		this.cause = cause;
	}
	
	public PebalmacenException(CodigosError codigo, String message) {
		this.codigo = codigo;
		this.message = message;
	}
	
	public PebalmacenException(CodigosError codigo, String message, Throwable cause) {
		this.codigo = codigo;
		this.message = message;
		this.cause = cause;
	}
}
