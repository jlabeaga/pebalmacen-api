package com.picosdeeuropaberries.pebalmacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.picosdeeuropaberries.pebalmacen.model.Socio;

@Repository
public interface SocioRepository extends CrudRepository<Socio, Integer> {

}
