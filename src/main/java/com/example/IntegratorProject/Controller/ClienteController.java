package com.example.IntegratorProject.Controller;

import com.example.IntegratorProject.Entities.Cliente;
import com.example.IntegratorProject.Entities.Empleado;
import com.example.IntegratorProject.Service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@Api(value = "cliente", description = "gestion de clientes")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @PreAuthorize("hasRole('WRITE')")
    @PostMapping("/cliente")
    @ApiOperation(value = "Crear cliente", response = Cliente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente creado exitosamente", response = Cliente.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Cliente.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Cliente.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Cliente.class)

    })
    public Cliente createCustomer(@RequestBody Cliente cliente){
        return this.clienteService.createCustomer(cliente);

    }

    @PreAuthorize("hasRole('WRITE')")
    @PutMapping("/cliente/{cedula}")
    @ApiOperation(value = "Actualizar un cliente exitosamente", response = Cliente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente fue actualizado exitosamente", response = Cliente.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Cliente.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Cliente.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Cliente.class)

    })
    public Cliente updateCustomer(@PathVariable Integer cedula, @RequestBody Cliente cliente){
        return this.clienteService.updateCustomer(cedula, cliente);
    }
    @PreAuthorize("hasRole('WRITE')")
    @DeleteMapping("/cliente/{cedula}")
    @ApiOperation(value = "Eliminar un cliente por cédula", response = Cliente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente fue eliminado exitosamente", response = Cliente.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Cliente.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Cliente.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Cliente.class)

    })
    public String deleteCustomer(@PathVariable Integer cedula){
        this.clienteService.deleteCustomer(cedula);
        return "El cliente con cédula " + cedula + " fue eliminado con éxito";
    }


    @PreAuthorize("hasRole('READ')")
    @GetMapping("/cliente/{cedula}")
    @ApiOperation(value = "Obtener un cliente por cédula", response = Cliente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente se obtuvo exitosamente", response = Cliente.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Cliente.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Cliente.class),
            @ApiResponse(code = 403, message = "Prohibido", response = Cliente.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Cliente.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Cliente.class)

    })
    public Cliente getCustomerByCedula(@PathVariable Integer cedula){
        return this.clienteService.getCustomerByCedula(cedula);
    }
}
