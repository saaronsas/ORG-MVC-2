package com.dev.springboot.service;

import com.dev.springboot.model.Marca;

import java.util.List;

/**
 * Interfaz del service, implementa las funciones de MarcaServiceImpl
 */
public interface IMarcaService {

    public Marca saveMarca(Marca marca);
    public List<Marca> getAllMarcas();
    public Marca getMarcaById(Integer id);
    public void deleteMarcaById(Integer id);
    public void updateMarca(Marca invoice);

}