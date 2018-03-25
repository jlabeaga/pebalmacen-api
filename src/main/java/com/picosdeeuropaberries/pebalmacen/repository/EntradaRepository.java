package com.picosdeeuropaberries.pebalmacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picosdeeuropaberries.pebalmacen.model.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Integer> {

}
