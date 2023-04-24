package com.example.IntegratorProject.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer cedula;
    private String nombre;
    private String apellido;
    private Long celular;
    private String correoElectronico;
    private String direccion;
    private String ciudad;
    private Integer antiguedadEmpresa;
    private String tipoSangre;
    private String tipoEmpleado;

    public Empleado() {
    }

    public Empleado(Integer cedula, String nombre, String apellido, Long celular, String correoElectronico, String direccion, String ciudad, Integer antiguedadEmpresa, String tipoSangre, String tipoEmpleado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.antiguedadEmpresa = antiguedadEmpresa;
        this.tipoSangre = tipoSangre;
        this.tipoEmpleado = tipoEmpleado;
    }

    public Integer getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Long getCelular() {
        return celular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Integer getAntiguedadEmpresa() {
        return antiguedadEmpresa;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setAntiguedadEmpresa(Integer antiguedadEmpresa) {
        this.antiguedadEmpresa = antiguedadEmpresa;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}
