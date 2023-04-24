package com.example.IntegratorProject.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "envios")
public class Envio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer numeroGuia;

    @ManyToOne
    @JoinColumn(name = "cedula")
    private Cliente cliente;
    private String ciudadOrigen;

    private String ciudadDestino;

    private String direccionDestino;

    private String nombreDestinatario;
    private Long numeroCelular;

    private String horaEntrega;

    private String estadoDelEnvio;
    private Integer valorEnvio;
    @OneToOne
    @JoinColumn(name = "idPaquete")
    private Paquete paquete;

    public Envio() {
    }

    public Envio(Cliente cliente, String ciudadOrigen, String ciudadDestino, String direccionDestino, String nombreDestinatario, Long numeroCelular, String horaEntrega, String estadoDelEnvio, Integer valorEnvio, Paquete paquete) {
        this.cliente = cliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.direccionDestino = direccionDestino;
        this.nombreDestinatario = nombreDestinatario;
        this.numeroCelular = numeroCelular;
        this.horaEntrega = horaEntrega;
        this.estadoDelEnvio = estadoDelEnvio;
        this.valorEnvio = valorEnvio;
        this.paquete = paquete;
    }


    public static  Integer calcularEnvio(String tipo){
        if(tipo == "GRANDE"){
            return 50000;
        } else if (tipo == "MEDIANO") {
            return 40000;
        } else if (tipo=="LIVIANO") {
            return 30000;
        }else{
            throw new RuntimeException("No existe coincidencias para ese tipo de paquete");
        }
    }
    public Integer getNumeroGuia() {
        return numeroGuia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public Long getNumeroCelular() {
        return numeroCelular;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public String getEstadoDelEnvio() {
        return estadoDelEnvio;
    }

    public Integer getValorEnvio() {
        return valorEnvio;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setNumeroGuia(Integer numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public void setNumeroCelular(Long numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public void setEstadoDelEnvio(String estadoDelEnvio) {
        this.estadoDelEnvio = estadoDelEnvio;
    }

    public void setValorEnvio(Integer valorEnvio) {
        this.valorEnvio = valorEnvio;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
}

