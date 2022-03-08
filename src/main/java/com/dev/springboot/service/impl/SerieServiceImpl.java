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

    @Autowired
    private SerieRepository repo;

    @Override
    public Serie saveSerie(Serie serie) {
        return repo.save(serie);
    }

    @Override
    public List<Serie> getAllSeries() {
        return repo.findAll();
    }

    @Override
    public Serie getSerieById(Integer id) {
        Optional<Serie> opt = repo.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            throw new SerieNotFoundException("Serie con Id : "+id+" no se ha encontrado");
        }
    }

    @Override
    public void deleteSerieById(Integer id) {
        repo.delete(getSerieById(id));
    }

    @Override
    public void updateSerie(Serie serie) {
        repo.save(serie);
    }
}