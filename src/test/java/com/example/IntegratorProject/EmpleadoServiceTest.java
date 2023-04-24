package com.example.IntegratorProject;

import com.example.IntegratorProject.Entities.Cliente;
import com.example.IntegratorProject.Entities.Empleado;
import com.example.IntegratorProject.Repository.EmpleadoRepository;
import com.example.IntegratorProject.Service.EmpleadoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmpleadoServiceTest {

    EmpleadoRepository empleadoRepository;

    private EmpleadoService empleadoService;

    @BeforeEach
    public void setUp(){
        this.empleadoRepository = mock(EmpleadoRepository.class);
        this.empleadoService = new EmpleadoService(empleadoRepository);
    }

    @Test
    void createSuccessEmployee(){
        //Arrange
        Empleado empleado = new Empleado(666, "Andres", "Gomez", 66655544L,"andres@gmail.com", "calle 1A","Cucuta", 3,  "O+", "REPARTIDOR");
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);
        //Act
        Empleado empleado1 = empleadoService.createEmployee(empleado);
        //Assert
        Assertions.assertSame(empleado1, empleado1);
    }

    @Test
    void createNullEmployee(){
        Empleado empleado = null;
        empleadoService.createEmployee(empleado);
    }

    @Test
    void createNullEmployeeId(){
        Empleado empleado = new Empleado(null,"Andres", "Gomez", 66655544L,"andres@gmail.com", "calle 1A","Cucuta", 3,  "O+", "REPARTIDOR");
        empleadoService.createEmployee(empleado);
    }


    @Test
    void createNullEmployeeName(){
        Empleado empleado = new Empleado(666, null, "Gomez", 66655544L,"andres@gmail.com", "calle 1A","Cucuta", 3,  "O+", "REPARTIDOR");
        empleadoService.createEmployee(empleado);
    }

    @Test
    void updateEmployee(){
        // Arrange
        Integer cedula = 443355;
        //Act
        Empleado empleadoExistente = new Empleado(null, "Andres", "Gomez", 66655544L,"andres@gmail.com", "calle 1A","Cucuta", 3,  "O+", "REPARTIDOR");
        when(empleadoRepository.findById(cedula)).thenReturn(Optional.of(empleadoExistente));

        Empleado empleadoActualizado = new Empleado();
        empleadoActualizado.setCedula(cedula);
        empleadoActualizado.setNombre(empleadoActualizado.getNombre());
        empleadoActualizado.setApellido(empleadoActualizado.getApellido());
        empleadoActualizado.setCelular(empleadoActualizado.getCelular());
        empleadoActualizado.setCorreoElectronico(empleadoActualizado.getCorreoElectronico());
        empleadoActualizado.setDireccion(empleadoActualizado.getDireccion());
        empleadoActualizado.setCiudad(empleadoActualizado.getDireccion());
        empleadoActualizado.setTipoEmpleado(empleadoActualizado.getTipoEmpleado());
        empleadoActualizado.setTipoSangre(empleadoActualizado.getTipoSangre());

        Empleado empleadoActualizado2 = empleadoService.updateEmployee(cedula, empleadoActualizado);

        // Verify
        verify(empleadoRepository, times(1)).findById(cedula);
    }
    @Test
    void DeleteExistingEmployee() {
        // Arrange

        Empleado empleado = new Empleado(666, "Andres", "Gomez", 66655544L,"andres@gmail.com", "calle 1A","Cucuta", 3,  "O+", "REPARTIDOR");
        empleadoRepository.save(empleado);

        // Act
        empleadoRepository.deleteById(empleado.getCedula());

        // Assert
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(666);
        assertFalse(optionalEmpleado.isPresent());
    }



    @Test
    void DeleteNonExistingEmployee() {
        // Arrange
        Integer cedula = 654322;

        // Act & Assert
        assertThrows(RuntimeException.class, () -> empleadoService.deleteEmployee(cedula));
    }

    @Test
    public void getExistingEmployeeByCedula() {
        // Arrange
        Integer cedula = 654322;
        Empleado empleado = new Empleado(cedula, "Andres", "Gomez", 66655544L,"andres@gmail.com", "calle 1A","Cucuta", 3,  "O+", "REPARTIDOR");

        when(empleadoRepository.findById(cedula)).thenReturn(Optional.of(empleado));
        empleadoRepository.save(empleado);

        // Act
        Empleado result = empleadoService.getEmployeeByCedula(cedula);

        // Assert
        assertNotNull(result);
        assertEquals(cedula, result.getCedula());
        assertEquals("Andres", result.getNombre());
    }


    

}
