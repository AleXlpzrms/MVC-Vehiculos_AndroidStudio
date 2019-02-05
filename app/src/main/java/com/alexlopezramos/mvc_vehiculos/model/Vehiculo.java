package com.alexlopezramos.mvc_vehiculos.model;

public class Vehiculo {

    private int id;
    private String marca;
    private String modelo;
    private String matricula;

    public Vehiculo() {}

    public Vehiculo( String matricula, String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
    }

    public Vehiculo(int id, String matricula, String marca, String modelo) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
