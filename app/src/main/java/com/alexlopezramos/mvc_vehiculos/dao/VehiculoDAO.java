package com.alexlopezramos.mvc_vehiculos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alexlopezramos.mvc_vehiculos.idao.VehiculoIDAO;
import com.alexlopezramos.mvc_vehiculos.model.Vehiculo;
import com.alexlopezramos.mvc_vehiculos.model.VehiculosSQLiteHelper;

import java.util.ArrayList;

public class VehiculoDAO implements VehiculoIDAO {

    // MODEL
    private VehiculosSQLiteHelper sqLiteHelper;
    private SQLiteDatabase db;
    private ArrayList<Vehiculo> vehiculos;

    //Constructor
    public VehiculoDAO(Context context) {
        sqLiteHelper = new VehiculosSQLiteHelper(context, "DBVehiculos", null, 1);
    }

    //Create
    @Override
    public boolean insertVehiculo(Vehiculo vehiculo) {
        //Insertar en BD
        db = sqLiteHelper.getWritableDatabase();
        if(db != null) {
            //Comprobar si la matricula existe
            boolean matriculaExists = false;
            Cursor c = db.rawQuery("SELECT matricula FROM vehiculos", null);
            if(c.moveToFirst()) {
                do {
                    if(c.getString(0).equals(vehiculo.getMatricula())) {
                        matriculaExists = true;
                    }
                } while(c.moveToNext());
            }
            //Si no existe inserta vehiculo
            if(!matriculaExists) {
                ContentValues registro = new ContentValues();
                registro.put("matricula", vehiculo.getMatricula());
                registro.put("marca", vehiculo.getMarca());
                registro.put("modelo", vehiculo.getModelo());
                db.insertOrThrow("vehiculos", null, registro);
            }
            db.close();
            return matriculaExists;
        }
        return false;
    }

    //Read
    @Override
    public ArrayList<Vehiculo> getVehiculos() {
        db = sqLiteHelper.getWritableDatabase();
        vehiculos = new ArrayList<Vehiculo>();
        if(db != null) {
            Cursor c = db.rawQuery("SELECT id, matricula, marca, modelo FROM vehiculos", null);
            if(c.moveToFirst()) {
                int id;
                String matricula, marca, modelo;
                do {
                    id = c.getInt(0);
                    matricula = c.getString(1);
                    marca = c.getString(2);
                    modelo = c.getString(3);
                    vehiculos.add(new Vehiculo(id, matricula, marca, modelo));
                } while(c.moveToNext());
            }
            db.close();
        }
        return vehiculos;
    }

    //Update
    @Override
    public boolean updateVehiculo(Vehiculo vehiculo) {
        //Actualizar en BD
        db = sqLiteHelper.getWritableDatabase();
        if(db != null) {
            //Comprobar si la matricula existe
            boolean matriculaExists = false;
            Cursor c = db.rawQuery("SELECT matricula FROM vehiculos", null);
            if(c.moveToFirst()) {
                do {
                    //Si existe actualiza vehiculo
                    if(c.getString(0).equals(vehiculo.getMatricula())) {
                        ContentValues registro = new ContentValues();
                        registro.put("matricula", vehiculo.getMatricula());
                        registro.put("marca", vehiculo.getMarca());
                        registro.put("modelo", vehiculo.getModelo());
                        String[] args = { vehiculo.getMatricula() };
                        db.update("vehiculos", registro, "matricula=?", args);
                        matriculaExists = true;
                    }
                } while(c.moveToNext());
            }
            db.close();
            return matriculaExists;
        }
        return false;
    }

    //Delete
    @Override
    public boolean deleteVehiculo(Vehiculo vehiculo) {
        //Borrar en BD
        db = sqLiteHelper.getWritableDatabase();
        if(db != null) {
            //Comprobar si la matricula existe
            boolean matriculaExists = false;
            Cursor c = db.rawQuery("SELECT matricula FROM vehiculos", null);
            if(c.moveToFirst()) {
                do {
                    //Si existe borra vehiculo
                    if(c.getString(0).equals(vehiculo.getMatricula())) {
                        String[] args = { vehiculo.getMatricula() };
                        db.delete("vehiculos", "matricula=?", args);
                        matriculaExists = true;
                    }
                } while(c.moveToNext());
            }
            db.close();
            return matriculaExists;
        }
        return false;
    }
}
