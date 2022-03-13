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

    /**
     * VehciuloRepository para poder acceder a las funciones
     */
    @Autowired
    private VehiculoRepository repo;

    /**
     * Guarda el vehiculo
     * @param vehiculo vehiculo a guardar
     * @return repo.save(vehiculo)
     */
    @Override
    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        return repo.save(vehiculo);
    }

    /**
     * Lista todos los vehiculos
     * @return repo.findAll()
     */
    @Override
    public List<Vehiculo> getAllVehiculos() {
        return repo.findAll();
    }

    /**
     * Devuelve un vehiculo atraves del identificador
     * @param id identificador del vehiculo a buscar
     * @return repo.findById(id)
     */
    @Override
    public Vehiculo getVehiculoById(Integer id) {
        Optional<Vehiculo> opt = repo.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            throw new VehiculoNotFoundException("Vehiculo con Id : "+id+" no se ha encontrado");
        }
    }

    /**
     * Elimina un vehiculo por el identificador
     * @param id identificador del vehiculo a borrar
     */
    @Override
    public void deleteVehiculoById(Integer id) {
        repo.delete(getVehiculoById(id));
    }

    /**
     * Actualiza un vehiuclo
     * @param vehiculo vehiculo a aztualizar
     */
    @Override
    public void updateVehiculo(Vehiculo vehiculo) {
        repo.save(vehiculo);
    }
}