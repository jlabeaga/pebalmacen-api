package com.picosdeeuropaberries.pebalmacen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.picosdeeuropaberries.pebalmacen.model.Transportista;

@Repository
public interface TransportistaRepository extends CrudRepository<Transportista, Integer> {

}
