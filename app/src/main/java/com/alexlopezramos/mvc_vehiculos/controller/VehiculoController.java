package com.alexlopezramos.mvc_vehiculos.controller;

import android.content.Context;

import com.alexlopezramos.mvc_vehiculos.dao.VehiculoDAO;
import com.alexlopezramos.mvc_vehiculos.model.Vehiculo;

import java.util.ArrayList;

public class VehiculoController {

    // CONTROLLER
    private Context context;

    public VehiculoController(Context context) {
        this.context = context;
    }

    // DAO
    private VehiculoDAO vehiculoDAO;

    //Create
    public boolean insertVehiculo(Vehiculo vehiculo) {
        vehiculoDAO = new VehiculoDAO(context);
        return vehiculoDAO.insertVehiculo(vehiculo);
    }

    //Read
    public ArrayList<Vehiculo> getVehiculos() {
        vehiculoDAO = new VehiculoDAO(context);
        return vehiculoDAO.getVehiculos();
    }

    //Update
    public boolean updateVehiculo(Vehiculo vehiculo) {
        vehiculoDAO = new VehiculoDAO(context);
        return vehiculoDAO.updateVehiculo(vehiculo);
    }

    //Delete
    public boolean deleteVehiculo(Vehiculo vehiculo) {
        vehiculoDAO = new VehiculoDAO(context);
        return vehiculoDAO.deleteVehiculo(vehiculo);
    }
}
