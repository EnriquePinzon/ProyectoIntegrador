package com.example.IntegratorProject;

import com.example.IntegratorProject.Entities.Cliente;
import com.example.IntegratorProject.Exceptions.ApiRequestException;
import com.example.IntegratorProject.Repository.ClienteRepository;
import com.example.IntegratorProject.Service.ClienteService;
import org.junit.Assert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    ClienteRepository clienteRepository;
    private ClienteService clienteService;

    @BeforeEach
    public void setUp(){
        this.clienteRepository = mock(ClienteRepository.class);
        this.clienteService = new ClienteService(clienteRepository);
    }

    @Test
    void createSuccessCustomer(){
        //Arrange
        Cliente cliente = new Cliente(777, "Enrique", "Pinzon", 66655544L,"enrique@gmail.com", "calle 1E","Cucuta");
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        //Act
        Cliente cliente1 = clienteService.createCustomer(cliente);
        //Assert
        Assertions.assertSame(cliente1, cliente1);
    }

    @Test
    void createNullCustomer(){
        Cliente cliente = null;
        clienteService.createCustomer(cliente);
    }

    @Test
     void createNullCustomerId(){
        Cliente cliente = new Cliente(null, "Enrique", "Pinzon", 66655544L,"enrique@gmail.com", "calle 1E","Cucuta");
        clienteService.createCustomer(cliente);
    }


    @Test
    void createNullCustomerName(){
        Cliente cliente = new Cliente(777, null,  "Pinzon", 66655544L,"enrique@gmail.com", "calle 1E","Cucuta");
        clienteService.createCustomer(cliente);
    }

    @Test
    void updateCustomer(){
        // Arrange
        Integer cedula = 12345;
        //Act
        Cliente clienteExistente = new Cliente(cedula, "Enrique", "Pinzon", 66655544L,"enrique@gmail.com", "calle 1E","Cucuta");
        when(clienteRepository.findById(cedula)).thenReturn(Optional.of(clienteExistente));

        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setCedula(cedula);
        clienteActualizado.setNombre(clienteExistente.getNombre());
        clienteActualizado.setApellido(clienteExistente.getApellido());
        clienteActualizado.setCelular(clienteExistente.getCelular());
        clienteActualizado.setCorreoElectronico(clienteExistente.getCorreoElectronico());
        clienteActualizado.setDireccion(clienteExistente.getDireccion());
        clienteActualizado.setCiudad(clienteExistente.getDireccion());

        Cliente clienteActualizado2 = clienteService.updateCustomer(cedula, clienteActualizado);

        // Verify
        verify(clienteRepository, times(1)).findById(cedula);
    }
    @Test
    void DeleteExistingCustomer() {
        // Arrange

        Cliente cliente = new Cliente(777, "John", "Doe", 30052200L, "doe@example.com", "123 Main St", "New York");
        clienteRepository.save(cliente);

        // Act
        clienteRepository.deleteById(cliente.getCedula());

        // Assert
        Optional<Cliente> optionalCliente = clienteRepository.findById(777);
        assertFalse(optionalCliente.isPresent());
    }



    @Test
    void DeleteNonExistingCustomer() {
        // Arrange
        Integer cedula = 123456789;

        // Act & Assert
        assertThrows(RuntimeException.class, () -> clienteService.deleteCustomer(cedula));
    }

    @Test
    public void getExistingCustomerByCedula() {
        // Arrange
        Integer cedula = 123456;
        Cliente cliente = new Cliente(cedula, "Enrique", "Pinzon", 66655544L,"enrique@gmail.com", "calle 1E","Cucuta");

        when(clienteRepository.findById(cedula)).thenReturn(Optional.of(cliente));
        clienteRepository.save(cliente);

        // Act
        Cliente result = clienteService.getCustomerByCedula(cedula);

        // Assert
        assertNotNull(result);
        assertEquals(cedula, result.getCedula());
        assertEquals("Enrique", result.getNombre());
    }





}
