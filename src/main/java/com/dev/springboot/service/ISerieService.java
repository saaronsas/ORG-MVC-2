package com.dev.springboot.service;


import com.dev.springboot.model.Serie;

import java.util.List;

public interface ISerieService {

    public Serie saveSerie(Serie serie);
    public List<Serie> getAllSeries();
    public Serie getSerieById(Integer id);
    public void deleteSerieById(Integer id);
    public void updateSerie(Serie serie);

}