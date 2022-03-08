package com.dev.springboot.service;


import com.dev.springboot.model.Vehiculo;

import java.util.List;

public interface IVehiculoService {

    public Vehiculo saveVehiculo(Vehiculo vehiculo);
    public List<Vehiculo> getAllVehiculos();
    public Vehiculo getVehiculoById(Integer id);
    public void deleteVehiculoById(Integer id);
    public void updateVehiculo(Vehiculo vehiculo);

}