package com.example.IntegratorProject.DTO;

import java.io.Serializable;

public class EnvioDtoUpdate implements Serializable {
    private Integer numeroGuia;
    private String  estadoEnvio;
    private Integer cedulaEmpleado;

    public EnvioDtoUpdate() {
    }

    public EnvioDtoUpdate(Integer numeroGuia, String estadoEnvio, Integer cedulaEmpleado) {
        this.numeroGuia = numeroGuia;
        this.estadoEnvio = estadoEnvio;
        this.cedulaEmpleado = cedulaEmpleado;
    }

    public Integer getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(Integer numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Integer getCedulaEmpleado() {
        return cedulaEmpleado;
    }

    public void setCedulaEmpleado(Integer cedulaEmpleado) {
        this.cedulaEmpleado = cedulaEmpleado;
    }
}
