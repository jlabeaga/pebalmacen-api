package com.picosdeeuropaberries.pebalmacen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picosdeeuropaberries.pebalmacen.service.CrudService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/crud")
@Slf4j
public class CrudController {
	
	@Autowired
	CrudService crudService;
	
	@GetMapping("/{entity_name}")
	public Iterable<Object> findAll(@PathVariable String entity_name) throws Exception{
		log.info("==> GET findAll: {}", entity_name);
		return crudService.findAll(entity_name);
	}
	
	@GetMapping("/{entity_name}/{id}")
	public Object findOne(@PathVariable String entity_name, @PathVariable Integer id) throws Exception{
		log.info("==> GET findOne: {} - {}", entity_name, id);
		return crudService.findOne(entity_name, id);
	}
	
	@PostMapping("/{entity_name}")
	public Object insert(@PathVariable String entity_name, @RequestBody Object entity) throws Exception{
		log.info("==> POST insert: {} - {}", entity_name, entity);
		return crudService.insert(entity_name, entity);
	}
	
	@PutMapping("/{entity_name}/{id}")
	public Object update(@PathVariable String entity_name, @PathVariable Integer id, @RequestBody Object entity) throws Exception{
		log.info("==> PUT update: {} - {} - {}", entity_name, id, entity);
		return crudService.update(entity_name, id, entity);
	}
	
	@DeleteMapping("/{entity_name}/{id}")
	public void delete(@PathVariable String entity_name, @PathVariable Integer id) throws Exception{
		log.info("==> DELETE delete: {}", id);
		crudService.delete(entity_name, id);
	}
}
