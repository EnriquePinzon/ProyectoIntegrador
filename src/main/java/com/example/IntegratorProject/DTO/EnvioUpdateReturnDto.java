package com.example.IntegratorProject.DTO;

import java.io.Serializable;

public class EnvioUpdateReturnDto implements Serializable {

    private Integer numeroGuia;
    private String ultimoEstado;

    public EnvioUpdateReturnDto() {
    }

    public EnvioUpdateReturnDto(Integer numeroGuia, String estadoEnvio) {
        this.numeroGuia = numeroGuia;
        this.ultimoEstado = estadoEnvio;
    }

    public Integer getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(Integer numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public String getUltimoEstado() {
        return ultimoEstado;
    }

    public void setUltimoEstado(String ultimoEstado) {
        this.ultimoEstado = ultimoEstado;
    }
}
