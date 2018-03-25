package com.picosdeeuropaberries.pebalmacen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picosdeeuropaberries.pebalmacen.crud.model.Test;
import com.picosdeeuropaberries.pebalmacen.exception.CodigosError;
import com.picosdeeuropaberries.pebalmacen.exception.ErrorUtils;
import com.picosdeeuropaberries.pebalmacen.model.EstadoEnvio;
import com.picosdeeuropaberries.pebalmacen.model.EstadoLote;
import com.picosdeeuropaberries.pebalmacen.model.EstadoPartida;
import com.picosdeeuropaberries.pebalmacen.model.EstadoPedido;
import com.picosdeeuropaberries.pebalmacen.model.TipoContacto;
import com.picosdeeuropaberries.pebalmacen.model.TipoDireccion;
import com.picosdeeuropaberries.pebalmacen.model.TipoEcologico;
import com.picosdeeuropaberries.pebalmacen.service.TestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/enums")
@Slf4j
public class EnumController {
	
	@GetMapping("/{enum_name}")
	public List<String> findAll(@PathVariable String enum_name) throws Exception{
		log.info("==> GET findAll: {}", enum_name);
		if( enum_name.equals("EstadoEnvio") ) {
			return Stream.of(EstadoEnvio.values()).map(Enum::name).collect(Collectors.toList());
		} else if( enum_name.equals("EstadoLote") ) {
			return Stream.of(EstadoLote.values()).map(Enum::name).collect(Collectors.toList());
		} else if( enum_name.equals("EstadoPartida") ) {
			return Stream.of(EstadoPartida.values()).map(Enum::name).collect(Collectors.toList());
		} else if( enum_name.equals("EstadoPedido") ) {
			return Stream.of(EstadoPedido.values()).map(Enum::name).collect(Collectors.toList());
		} else if( enum_name.equals("TipoContacto") ) {
			return Stream.of(TipoContacto.values()).map(Enum::name).collect(Collectors.toList());
		} else if( enum_name.equals("TipoDireccion") ) {
			return Stream.of(TipoDireccion.values()).map(Enum::name).collect(Collectors.toList());
		} else if( enum_name.equals("TipoEcologico") ) {
			return Stream.of(TipoEcologico.values()).map(Enum::name).collect(Collectors.toList());
		}
		throw ErrorUtils.logAndBuildException(
			CodigosError.GENERAL_DATO_NO_ENCONTRADO, 
			"Error: no se encuentra el Enum: " + enum_name);
	}
	
}
