package com.picosdeeuropaberries.pebalmacen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.picosdeeuropaberries.pebalmacen.model.Contacto;

@RepositoryRestResource
public interface ContactoRepository extends CrudRepository<Contacto, Integer> {

}
