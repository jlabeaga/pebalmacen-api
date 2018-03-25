package com.picosdeeuropaberries.pebalmacen.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandlingController {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(EmptyResultDataAccessException e) {
        ExceptionResponse response = buildExceptionResponse(CodigosError.GENERAL_DATO_NO_ENCONTRADO, e);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PebalmacenException.class)
    public ResponseEntity<ExceptionResponse> PebalmacenException(PebalmacenException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCodigo(e.getCodigo().toString());
        //TODO: añadir descripciones a CodigosError
        response.setDescripcion(e.getCodigo().toString());
        response.setTicket(e.getTicket());
        response.setMessage(e.getMessage());
        if( e.getCause() != null ) {
        	response.setCauseMessage(e.getCause().getClass().getName() + " : " + e.getCause().getMessage());
        	response.setCause(e.getCause());
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> dataIntegrityViolationException(DataIntegrityViolationException e) {
        ExceptionResponse response = buildExceptionResponse(CodigosError.GENERAL_ERROR_INTEGRIDAD_DATOS, e);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
	private ExceptionResponse buildExceptionResponse(CodigosError codigoError, Throwable e) {
		ExceptionResponse response = new ExceptionResponse();
        response.setCodigo(codigoError.toString());
        //TODO: añadir descripciones a CodigosError
        response.setDescripcion(codigoError.toString());
        
        log.error(response.toString());
        log.error("Causa: " + e);
        
        return response;
	}
	

    
}
