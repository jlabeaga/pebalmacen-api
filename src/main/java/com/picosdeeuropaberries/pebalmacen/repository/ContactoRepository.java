package com.picosdeeuropaberries.pebalmacen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.picosdeeuropaberries.pebalmacen.model.Contacto;

@Repository
public interface ContactoRepository extends CrudRepository<Contacto, Integer> {

}
