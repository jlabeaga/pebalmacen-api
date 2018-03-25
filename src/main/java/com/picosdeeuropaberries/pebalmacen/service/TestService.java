package com.picosdeeuropaberries.pebalmacen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picosdeeuropaberries.pebalmacen.crud.model.Test;
import com.picosdeeuropaberries.pebalmacen.repository.TestRepository;

@Service
@Transactional(rollbackFor=Exception.class)
public class TestService {
	
	@Autowired
	TestRepository testRepository;
	
	public Iterable<Test> findAll() throws Exception{
		return testRepository.findAll();
	}
	
	public Test findOne(Integer id) throws Exception{
		return testRepository.findOne(id);
	}
	
	public Test insert(Test test) throws Exception{
		test.setId(null);
		return testRepository.save(test);
	}

	public Test update(Test test) throws Exception{
		return testRepository.save(test);
	}

	public void delete(Integer id) throws Exception{
		testRepository.delete(id);
	}
}
