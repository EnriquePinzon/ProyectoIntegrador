package com.example.IntegratorProject.DTO;

import java.io.Serializable;

public class EnvioDTO implements Serializable{
    private Integer numeroGuia;
    private String estadoDelEnvio;


    public EnvioDTO() {
    }

    public EnvioDTO(Integer numeroGuia, String estadoDelEnvio) {
        this.numeroGuia = numeroGuia;
        this.estadoDelEnvio = estadoDelEnvio;
    }

    public Integer getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(Integer numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public String getEstadoDelEnvio() {
        return estadoDelEnvio;
    }

    public void setEstadoDelEnvio(String estadoDelEnvio) {
        this.estadoDelEnvio = estadoDelEnvio;
    }
}
