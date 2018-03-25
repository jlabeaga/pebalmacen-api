package com.picosdeeuropaberries.pebalmacen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.picosdeeuropaberries.pebalmacen.crud.model.Test;

@Repository
public interface TestRepository extends CrudRepository<Test, Integer> {

}
