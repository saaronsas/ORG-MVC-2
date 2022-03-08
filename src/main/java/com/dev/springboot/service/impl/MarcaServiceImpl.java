package com.dev.springboot.service.impl;

import com.dev.springboot.exception.MarcaNotFoundException;
import com.dev.springboot.model.Marca;
import com.dev.springboot.repo.MarcaRepository;
import com.dev.springboot.service.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements IMarcaService{

    @Autowired
    private MarcaRepository repo;

    @Override
    public Marca saveMarca(Marca invoice) {
        return repo.save(invoice);
    }

    @Override
    public List<Marca> getAllMarcas() {
        return repo.findAll();
    }

    @Override
    public Marca getMarcaById(Integer id) {
        Optional<Marca> opt = repo.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            throw new MarcaNotFoundException("Marca with Id : "+id+" Not Found");
        }
    }

    @Override
    public void deleteMarcaById(Integer id) {
        repo.delete(getMarcaById(id));
    }

    @Override
    public void updateMarca(Marca marca) {
        repo.save(marca);
    }
}