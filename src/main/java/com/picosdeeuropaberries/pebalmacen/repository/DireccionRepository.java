package com.picosdeeuropaberries.pebalmacen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.picosdeeuropaberries.pebalmacen.model.Contacto;
import com.picosdeeuropaberries.pebalmacen.model.Direccion;

@RepositoryRestResource(path="direcciones")
public interface DireccionRepository extends CrudRepository<Direccion, Integer> {

}
