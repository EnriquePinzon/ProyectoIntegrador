package com.example.IntegratorProject;

import com.example.IntegratorProject.DTO.*;
import com.example.IntegratorProject.Entities.Cliente;
import com.example.IntegratorProject.Entities.Empleado;
import com.example.IntegratorProject.Entities.Envio;
import com.example.IntegratorProject.Entities.Paquete;
import com.example.IntegratorProject.Exceptions.ApiRequestException;
import com.example.IntegratorProject.Repository.ClienteRepository;
import com.example.IntegratorProject.Repository.EmpleadoRepository;
import com.example.IntegratorProject.Repository.EnvioRepository;
import com.example.IntegratorProject.Repository.PaqueteRepository;
import com.example.IntegratorProject.Service.EnvioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class EnvioServiceTest {
    EnvioRepository envioRepository;
    EmpleadoRepository empleadoRepository;
    ClienteRepository clienteRepository;
    EnvioService envioService;
    PaqueteRepository paqueteRepository;


    @BeforeEach
    void setUp(){
        this.clienteRepository = mock(ClienteRepository.class);
        this.empleadoRepository = mock(EmpleadoRepository.class);
        this.envioRepository = mock(EnvioRepository.class);
        this.paqueteRepository = mock(PaqueteRepository.class);
        this.envioService = new EnvioService(envioRepository,clienteRepository,empleadoRepository,paqueteRepository);
    }



    @Test
    void CreateShipment_success() {
        // Arrange
        EnvioDtoRequest envioDtoRequest = new EnvioDtoRequest();
        envioDtoRequest.setCedulaCliente(123);
        envioDtoRequest.setNombreRecibe("Jane Doe");
        envioDtoRequest.setCiudadOrigen("Bogotá");
        envioDtoRequest.setCiudadDestino("Medellín");
        envioDtoRequest.setValorDeclarado(50000);
        envioDtoRequest.setPeso(2);
        envioDtoRequest.setCelularRecibe(321515124L);
        envioDtoRequest.setDirDestino("456 Oak St");

        Cliente cliente = new Cliente();
        cliente.setCedula(123);
        when(clienteRepository.findById(anyInt())).thenReturn(Optional.of(cliente));

        Paquete paquete = new Paquete();
        paquete.setTipoPaquete("RECIBIDO");
        when(paqueteRepository.save(any(Paquete.class))).thenReturn(paquete);

        Envio envio = new Envio();
        envio.setNumeroGuia(1);
        when(envioRepository.save(any(Envio.class))).thenReturn(envio);

        // Act
        EnvioDTO result = envioService.createShipment(envioDtoRequest);


        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(envio.getNumeroGuia(), result.getNumeroGuia());
    }



    @Test
    void createShipmentWithInvalidData() {
        // Arrange
        EnvioDtoRequest envioDtoRequest = new EnvioDtoRequest();
        envioDtoRequest.setCedulaCliente(null);
        envioDtoRequest.setNombreRecibe("John Doe");
        envioDtoRequest.setCiudadOrigen("Bogotá");
        envioDtoRequest.setCiudadDestino("Medellín");
        envioDtoRequest.setValorDeclarado(10000);
        envioDtoRequest.setPeso(1.5);
        envioDtoRequest.setDirDestino("123 Main St");

        // Act & Assert
        assertThrows(RuntimeException.class, () -> envioService.createShipment(envioDtoRequest));
    }

    @Test
    void createShipmentWithUnregisteredCustomer() {
        // Arrange
        EnvioDtoRequest envioDtoRequest = new EnvioDtoRequest();
        envioDtoRequest.setCedulaCliente(123456789);
        envioDtoRequest.setNombreRecibe("John Doe");
        envioDtoRequest.setCiudadOrigen("Bogotá");
        envioDtoRequest.setCiudadDestino("Medellín");
        envioDtoRequest.setValorDeclarado(50000);
        envioDtoRequest.setPeso(2);
        envioDtoRequest.setDirDestino("123 Main St");

        // Act & Assert
        assertThrows(RuntimeException.class, () -> envioService.createShipment(envioDtoRequest));
    }

    @Test
    void CreateShipment_missingInformation() {
        // Arrange
        EnvioDtoRequest request = new EnvioDtoRequest();
        request.setNombreRecibe("John Doe");
        request.setCiudadOrigen("Bogotá");

        // Act & Assert
        assertThrows(RuntimeException.class, () -> envioService.createShipment(request));
    }

    @Test
    public void searchExistingShipment() {
        //Arrange
        Integer numGuia = 8899;

        Cliente cliente = new Cliente();
        cliente.setCedula(123456789);
        cliente.setNombre("Andres");
        cliente.setApellido("Gomez");
        cliente.setCelular(3543551L);
        cliente.setDireccion("Calle AB # 5-50");
        cliente.setCorreoElectronico("andres@mail.com");
        cliente.setCiudad("bucaramanga");

        Paquete paquete = new Paquete();
        paquete.setTipoPaquete("MEDIANO");
        paquete.setPeso(3.3);
        paquete.setValorDeclarado(50.000);

        Envio envio = new Envio();
        envio.setCiudadOrigen("Bogotá");
        envio.setCiudadDestino("Medellín");
        envio.setDireccionDestino("Carrera 1F # 10 - 50");
        envio.setNombreDestinatario("Juan Perez");
        envio.setNumeroCelular(11111111L);
        envio.setHoraEntrega("18:00");
        envio.setEstadoDelEnvio("RECIBIDO");
        envio.setCliente(cliente);
        envio.setPaquete(paquete);

        //Act
        when(envioRepository.findById(any())).thenReturn(Optional.of(envio));

        // Assert
        EnvioDtoInfo respuestaDTO = envioService.getInfoShipment(numGuia);
        assertNotNull(respuestaDTO);

    }
    @Test
    void getShipmentInfoNonExisting() {
        // Arrange
        Integer numeroGuia = 9999999;

        // Assert
        assertThrows(RuntimeException.class, () -> {
            // Act
            envioService.getInfoShipment(numeroGuia);
        });
    }
    @Test
    void updatePackageStatus() {
        // setup
        Integer cedulaEmpleado = 123456;
        int numeroGuia = 1;
        String estadoInicial = "RECIBIDO";
        String estadoFinal = "EN RUTA";

        Empleado repartidor = new Empleado();
        repartidor.setCedula(cedulaEmpleado);
        repartidor.setTipoEmpleado("REPARTIDOR");

        Envio envio = new Envio();
        envio.setNumeroGuia(numeroGuia);
        envio.setEstadoDelEnvio(estadoInicial);

        EnvioDtoUpdate envioDtoUpdate = new EnvioDtoUpdate();
        envioDtoUpdate.setCedulaEmpleado(cedulaEmpleado);
        envioDtoUpdate.setNumeroGuia(numeroGuia);
        envioDtoUpdate.setEstadoEnvio(estadoFinal);

        // mockito stubbing
        when(empleadoRepository.findById(cedulaEmpleado)).thenReturn(Optional.of(repartidor));
        when(envioRepository.findById(numeroGuia)).thenReturn(Optional.of(envio));

        // execute
        EnvioUpdateReturnDto result = envioService.updatePackageStatus(envioDtoUpdate);

        // verify
        assertEquals(numeroGuia, result.getNumeroGuia());
        assertEquals(estadoFinal, result.getUltimoEstado());
        assertEquals(estadoFinal, envio.getEstadoDelEnvio());
    }

    @Test
    void updatePackageStatusInvalidEmployeeType() {
        // Arrange
        EnvioDtoUpdate envioDtoUpdate = new EnvioDtoUpdate();
        envioDtoUpdate.setNumeroGuia(1234);
        envioDtoUpdate.setEstadoEnvio("EN RUTA");
        envioDtoUpdate.setCedulaEmpleado(99999999); // empleado no existente

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            envioService.updatePackageStatus(envioDtoUpdate);
        });

        String expectedMessage = "Solo los empleados de tipo REPARTIDOR y COORDINADOR pueden cambiar el estado del paquete";
        String actualMessage = exception.getMessage();

        assertFalse(actualMessage.contains(expectedMessage));
    }


    @Test
    public void testUpdatePackageStatusOnlyAllowedEmployees() {
        // Crear un empleado que no es de tipo "REPARTIDOR" ni "COORDINADOR"
        Empleado empleado = new Empleado();
        empleado.setCedula(123456789);
        empleado.setNombre("Marcos Alonso");
        empleado.setTipoEmpleado("CONDUCTOR");
        empleadoRepository.save(empleado);

        // Crear un envío con estado "RECIBIDO"
        Envio envio = new Envio();
        envio.setNumeroGuia(123456);
        envio.setEstadoDelEnvio("RECIBIDO");
        envioRepository.save(envio);

        // Crear un DTO para actualizar el estado del envío a "EN RUTA"
        EnvioDtoUpdate envioDtoUpdate = new EnvioDtoUpdate();
        envioDtoUpdate.setNumeroGuia(123456);
        envioDtoUpdate.setEstadoEnvio("EN RUTA");
        envioDtoUpdate.setCedulaEmpleado(empleado.getCedula());

        // Verificar que al tratar de actualizar el estado del envío con un empleado que no es de tipo "REPARTIDOR" ni "COORDINADOR",
        // se lanza una excepción
        assertThrows(RuntimeException.class, () -> {
            envioService.updatePackageStatus(envioDtoUpdate);
        });
    }


    @Test
    void testGetEnviosByStatusAndIdEmployee_success() {
        // Arrange
        String estadoDelEnvio = "RECIBIDO";
        Integer cedula = 123;
        Empleado empleado = new Empleado();
        empleado.setTipoSangre("O+");
        empleado.setTipoEmpleado("COORDINADOR");
        empleado.setCiudad("Bucaramanga");
        empleado.setDireccion("AN # 1 - B");
        empleado.setCelular(14581248L);
        empleado.setNombre("Javier");
        empleado.setApellido("Pereira");
        empleado.setAntiguedadEmpresa(2);
        empleado.setCorreoElectronico("javier@gmail.com");

        when(empleadoRepository.findById(cedula)).thenReturn(Optional.of(empleado));
        when(envioRepository.filtroPorEstado(estadoDelEnvio)).thenReturn(Arrays.asList(new Envio(), new Envio()));

        // Act
        List<Envio> result = envioService.getEnviosByStatusAndIdEmployee(estadoDelEnvio, cedula);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(empleadoRepository, times(1)).findById(cedula);
        verify(envioRepository, times(1)).filtroPorEstado(estadoDelEnvio);
    }

    @Test
    void testGetEnviosByStatusAndIdEmployee_employeeNotFound() {
        // Arrange
        String estadoDelEnvio = "RECIBIDO";
        Integer cedula = 123;
        when(empleadoRepository.findById(cedula)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> envioService.getEnviosByStatusAndIdEmployee(estadoDelEnvio, cedula));
    }

}
