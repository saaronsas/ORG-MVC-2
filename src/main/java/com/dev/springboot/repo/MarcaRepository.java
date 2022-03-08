package com.dev.springboot.repo;

import com.dev.springboot.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}