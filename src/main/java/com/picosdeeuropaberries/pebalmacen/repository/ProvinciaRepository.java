package com.picosdeeuropaberries.pebalmacen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.picosdeeuropaberries.pebalmacen.model.Provincia;

@Repository
public interface ProvinciaRepository extends CrudRepository<Provincia, Integer> {

}
