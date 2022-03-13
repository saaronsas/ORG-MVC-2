package com.dev.springboot.service.impl;

import com.dev.springboot.exception.SerieNotFoundException;
import com.dev.springboot.model.Serie;
import com.dev.springboot.repo.SerieRepository;
import com.dev.springboot.service.ISerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieServiceImpl implements ISerieService{

    /**
     * SerieRepository para poder acceder a las funciones
     */
    @Autowired
    private SerieRepository repo;

    /**
     * Guarda la serie
     * @param serie serie a guardar
     * @return repo.save(serie)
     */
    @Override
    public Serie saveSerie(Serie serie) {
        return repo.save(serie);
    }

    /**
     * Lista todas las series
     * @return repo.findAll()
     */
    @Override
    public List<Serie> getAllSeries() {
        return repo.findAll();
    }

    /**
     * Devuelve una serie atraves del identificador
     * @param id identificador de la serie a buscar
     * @return repo.findById(id)
     */
    @Override
    public Serie getSerieById(Integer id) {
        Optional<Serie> opt = repo.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            throw new SerieNotFoundException("Serie con Id : "+id+" no se ha encontrado");
        }
    }

    /**
     * Elimina una serie por el identificador
     * @param id identificador de la serie a borrar
     */
    @Override
    public void deleteSerieById(Integer id) {
        repo.delete(getSerieById(id));
    }

    /**
     * Actualiza una serie
     * @param serie serie a aztualizar
     */
    @Override
    public void updateSerie(Serie serie) {
        repo.save(serie);
    }
}