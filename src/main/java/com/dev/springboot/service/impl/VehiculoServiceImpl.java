package com.dev.springboot.service.impl;

import com.dev.springboot.exception.VehiculoNotFoundException;
import com.dev.springboot.model.Vehiculo;
import com.dev.springboot.repo.VehiculoRepository;
import com.dev.springboot.service.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements IVehiculoService{

    @Autowired
    private VehiculoRepository repo;

    @Override
    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        return repo.save(vehiculo);
    }

    @Override
    public List<Vehiculo> getAllVehiculos() {
        return repo.findAll();
    }

    @Override
    public Vehiculo getVehiculoById(Integer id) {
        Optional<Vehiculo> opt = repo.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            throw new VehiculoNotFoundException("Vehiculo con Id : "+id+" no se ha encontrado");
        }
    }

    @Override
    public void deleteVehiculoById(Integer id) {
        repo.delete(getVehiculoById(id));
    }

    @Override
    public void updateVehiculo(Vehiculo vehiculo) {
        repo.save(vehiculo);
    }
}