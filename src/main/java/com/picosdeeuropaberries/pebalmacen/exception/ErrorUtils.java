package com.picosdeeuropaberries.pebalmacen.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Crea una excepcion, escribe informacion sobre el error en el log y devuelve la excepcion generada
 */
@Slf4j
public class ErrorUtils {

	/**
	 * Crea una excepcion, escribe informacion sobre el error en el log y devuelve la excepcion generada
	 * 
	 * @param message mensaje adicional opcional sobre el error
	 * @param cause excepcion original opcional causante del error
	 * @return exepcion generada para ser tratada más adelante
	 */
	public static PebalmacenException logAndBuildException(CodigosError codigo, String message, Throwable cause) {
		PebalmacenException e = new PebalmacenException(codigo, message, cause);
		log.error("INICIO: " + e.getTicket());
		log.error("codigo: " + codigo.toString());
		if( message != null ) {
			log.error("message: " + message);
		}
		if( cause != null ) {
			log.error("cause: " + cause);
		}
		log.error("FIN: " + e.getTicket());
		return e;
	}
	
	public static PebalmacenException logAndBuildException(CodigosError codigo, String message) {
		return logAndBuildException(codigo, message, null);
	}
	
	public static PebalmacenException logAndBuildException(CodigosError codigo) {
		return logAndBuildException(codigo, null, null);
	}
}
