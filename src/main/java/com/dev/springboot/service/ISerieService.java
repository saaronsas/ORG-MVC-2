package com.dev.springboot.service;


import com.dev.springboot.model.Serie;

import java.util.List;

/**
 * Interfaz del service, implementa las funciones de SerieServiceImpl
 */
public interface ISerieService {

    public Serie saveSerie(Serie serie);
    public List<Serie> getAllSeries();
    public Serie getSerieById(Integer id);
    public void deleteSerieById(Integer id);
    public void updateSerie(Serie serie);

}