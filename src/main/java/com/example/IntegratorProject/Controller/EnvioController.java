package com.example.IntegratorProject.Controller;

import com.example.IntegratorProject.DTO.*;
import com.example.IntegratorProject.Entities.Cliente;
import com.example.IntegratorProject.Entities.Envio;
import com.example.IntegratorProject.Service.EnvioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EnvioController {

    private final EnvioService envioService;

    @Autowired
    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }
    @PreAuthorize("hasRole('WRITE')")
    @PostMapping("/envio")
    @ApiOperation(value = "Crear envio", response = Envio.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Envío creado exitosamente", response = Envio.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Envio.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Envio.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Envio.class)

    })
    public EnvioDTO createShipment(@RequestBody EnvioDtoRequest envioDtoRequest) {
        return this.envioService.createShipment(envioDtoRequest);
    }
    @PreAuthorize("hasRole('READ')")
    @GetMapping("/envio/{numeroGuia}")
    @ApiOperation(value = "Obtener un envio", response = Envio.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El envío fue encontrado", response = Envio.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Envio.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Envio.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Envio.class)

    })
    public EnvioDtoInfo getInfoShipment(@PathVariable Integer numeroGuia){
        return envioService.getInfoShipment(numeroGuia);
    }
    @PreAuthorize("hasRole('WRITE')")
    @PutMapping("/envio/estado")
    @ApiOperation(value = "Actualizar un envio", response = Envio.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Envío fue actualizado exitosamente", response = Envio.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Envio.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Envio.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Envio.class)

    })
    public EnvioUpdateReturnDto updatePackageStatus(@RequestBody EnvioDtoUpdate envioDtoUpdate){
        return this.envioService.updatePackageStatus(envioDtoUpdate);
    }

    @PreAuthorize("hasRole('READ')")
    @GetMapping("/envio")
    @ApiOperation(value = "Obtener envío por cédula del empleado y estado del envío", response = Envio.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Envío fue encontrado exitosamente", response = Envio.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Envio.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Envio.class),
            @ApiResponse(code = 403, message = "Prohibido", response = Envio.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Envio.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Envio.class)

    })
    public List<Envio> getEnviosByStatusAndIdEmployee(@RequestParam ("cedula") Integer cedula, @RequestParam ("estadoDelEnvio") String estadoDelEnvio){
        return this.envioService.getEnviosByStatusAndIdEmployee(estadoDelEnvio, cedula);
    }




}
