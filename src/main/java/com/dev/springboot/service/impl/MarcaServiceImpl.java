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

    /**
     * MarcaRepository para poder acceder a las funciones
     */
    @Autowired
    private MarcaRepository repo;

    /**
     * Guarda la marca
     * @param marca marca a guardar
     * @return repo.save(marca)
     */
    @Override
    public Marca saveMarca(Marca marca) {
        return repo.save(marca);
    }

    /**
     * Lista todas las marcas
     * @return repo.findAll()
     */
    @Override
    public List<Marca> getAllMarcas() {
        return repo.findAll();
    }

    /**
     * Devuelve una marca atraves del identificador
     * @param id identificador de la marca a buscar
     * @return repo.findById(id)
     */
    @Override
    public Marca getMarcaById(Integer id) {
        Optional<Marca> opt = repo.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            throw new MarcaNotFoundException("Marca with Id : "+id+" Not Found");
        }
    }

    /**
     * Elimina una marca por el identificador
     * @param id identificador de la marca a borrar
     */
    @Override
    public void deleteMarcaById(Integer id) {
        repo.delete(getMarcaById(id));
    }

    /**
     * Actualiza una marca
     * @param marca marca a aztualizar
     */
    @Override
    public void updateMarca(Marca marca) {
        repo.save(marca);
    }
}