package com.example.IntegratorProject.Entities;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "paquetes")
public class Paquete implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPaquete")
    private Integer idPaquete;

    @Column(name = "tipoPaquete")
    private String tipoPaquete;
    @Column(name = "peso")
    private Double peso;
    @Column(name = "valorDeclarado")
    private Double valorDeclarado;

    public Paquete() {
    }

    public Paquete(String tipoPaquete, Double peso, Double valorDeclarado) {
        this.tipoPaquete = tipoPaquete;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public void setTipoPaquete(String tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setValorDeclarado(Double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "idPaquete=" + idPaquete +
                ", tipoPaquete='" + tipoPaquete + '\'' +
                ", peso=" + peso +
                ", valorDeclarado=" + valorDeclarado +
                '}';
    }

    public static String tipoDePaquete(Double peso){
        if(peso<2.0){
            return "LIVIANO";
        } else if (peso>2.0 && peso<5.0) {
            return "MEDIANO";
        }
        return "GRANDE";
    }
}
