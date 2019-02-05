package com.alexlopezramos.mvc_vehiculos.idao;

import com.alexlopezramos.mvc_vehiculos.model.Vehiculo;

import java.util.ArrayList;

public interface VehiculoIDAO {

    //Create
    boolean insertVehiculo(Vehiculo vehiculo);

    //Read
    ArrayList<Vehiculo> getVehiculos();

    //Update
    boolean updateVehiculo(Vehiculo vehiculo);

    //Delete
    boolean deleteVehiculo(Vehiculo vehiculo);
}
