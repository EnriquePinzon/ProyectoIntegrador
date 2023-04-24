package com.example.IntegratorProject.DTO;

import java.io.Serializable;

public class EnvioDtoRequest implements Serializable {
    private Integer cedulaCliente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private  String dirDestino;
    private String nombreRecibe;
    private Long celularRecibe;
    private double peso;
    private double valorDeclarado;
    public EnvioDtoRequest(Integer cedulaCliente, String ciudadOrigen, String ciudadDestino, String dirDestino, String nombreRecibe, Long celularRecibe, double peso, double valorDeclarado) {
        this.cedulaCliente = cedulaCliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.dirDestino = dirDestino;
        this.nombreRecibe = nombreRecibe;
        this.celularRecibe = celularRecibe;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public EnvioDtoRequest() {
    }

    public Integer getCedulaCliente() {
        return cedulaCliente;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public String getDirDestino() {
        return dirDestino;
    }

    public String getNombreRecibe() {
        return nombreRecibe;
    }

    public Long getCelularRecibe() {
        return celularRecibe;
    }

    public double getPeso() {
        return peso;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setCedulaCliente(Integer cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public void setDirDestino(String dirDestino) {
        this.dirDestino = dirDestino;
    }

    public void setNombreRecibe(String nombreRecibe) {
        this.nombreRecibe = nombreRecibe;
    }

    public void setCelularRecibe(Long celularRecibe) {
        this.celularRecibe = celularRecibe;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }
}
