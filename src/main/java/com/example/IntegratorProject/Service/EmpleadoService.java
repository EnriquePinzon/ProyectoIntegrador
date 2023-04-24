package com.example.IntegratorProject.Service;

import com.example.IntegratorProject.Entities.Empleado;
import com.example.IntegratorProject.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoService {

    private EmpleadoRepository empleadoRepository;
    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado createEmployee(Empleado empleado){
        return this.empleadoRepository.save(empleado);
    }

    public Empleado updateEmployee(Integer cedula, Empleado empleado){
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(cedula);
        if(optionalEmpleado.isPresent()){
            optionalEmpleado.get().setCedula(empleado.getCedula());
            optionalEmpleado.get().setNombre(empleado.getNombre());
            optionalEmpleado.get().setApellido(empleado.getApellido());
            optionalEmpleado.get().setCelular(empleado.getCelular());
            optionalEmpleado.get().setCorreoElectronico(empleado.getCorreoElectronico());
            optionalEmpleado.get().setDireccion(empleado.getDireccion());
            optionalEmpleado.get().setCiudad(empleado.getCiudad());
            optionalEmpleado.get().setAntiguedadEmpresa(empleado.getAntiguedadEmpresa());
            optionalEmpleado.get().setTipoSangre(empleado.getTipoSangre());
            optionalEmpleado.get().setTipoEmpleado(empleado.getTipoEmpleado());
            return this.empleadoRepository.save(optionalEmpleado.get());
        }else{
            throw new RuntimeException("El empleado con cedula " + cedula + "no fue encontrado");
        }
    }

    public void deleteEmployee(Integer cedula){
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(cedula);
        if(optionalEmpleado.isPresent()){
            empleadoRepository.delete(optionalEmpleado.get());
        }else{
            throw new RuntimeException("El empleado con cedula "+ cedula + "no fue posible encontrarlo");
        }
    }

    public Empleado getEmployeeByCedula(Integer cedula){
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(cedula);
        if(optionalEmpleado.isPresent()){
            return optionalEmpleado.get();
        }else{
            throw  new RuntimeException("El empleado con cedula "+ cedula + "no fue posible encontrarlo");
        }
    }

}
