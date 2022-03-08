package com.dev.springboot.repo;

import com.dev.springboot.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Integer> {

}