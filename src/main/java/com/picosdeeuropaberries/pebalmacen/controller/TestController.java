package com.picosdeeuropaberries.pebalmacen.controller;

import java.util.List;

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
import com.picosdeeuropaberries.pebalmacen.service.TestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tests")
@Slf4j
public class TestController {
	
	@Autowired
	TestService testService;
	
	@GetMapping("/")
	public Iterable<Test> findAll() throws Exception{
		log.info("+++++ GET findAll:");
		Iterable<Test> test = testService.findAll();
		return test;
	}
	
	@GetMapping("/{id}")
	public Test findOne(@PathVariable Integer id) throws Exception{
		log.info("+++++ GET findById: {}", id);
		Test test = testService.findOne(id);
		return test;
	}
	
	@PostMapping("/")
	public Test insert(@RequestBody @Validated Test test) throws Exception{
		log.info("+++++ POST insert: {}", test);
		return testService.insert(test);
	}
	
	@PutMapping("/{id}")
	public Test update(@PathVariable Integer id, @RequestBody @Validated Test test) throws Exception{
		log.info("+++++ PUT update: {} - {}", id, test);
		test.setId(id);
		return testService.update(test);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) throws Exception{
		log.info("+++++ DELETE delete: {}", id);
		testService.delete(id);
	}
}
