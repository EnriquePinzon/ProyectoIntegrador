package com.example.IntegratorProject.Controller;

import com.example.IntegratorProject.Entities.Cliente;
import com.example.IntegratorProject.Entities.Empleado;
import com.example.IntegratorProject.Service.EmpleadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class EmpleadoController {
    private EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }
    @PreAuthorize("hasRole('WRITE')")
    @PostMapping("/empleado")
    @ApiOperation(value = "Crear empleado", response = Cliente.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Empleado fue creado exitosamente", response = Empleado.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Empleado.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Empleado.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Empleado.class)

    })
    public Empleado createEmployee(@RequestBody Empleado empleado){
        return this.empleadoService.createEmployee(empleado);
    }
    @PreAuthorize("hasRole('WRITE')")
    @PutMapping("/empleado/{cedula}")
    @ApiOperation(value = "Actualizar un empleado exitosamente", response = Empleado.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Empleado fue actualizado exitosamente", response = Empleado.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Empleado.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Empleado.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Empleado.class)

    })
    public Empleado updateEmployee(@PathVariable Integer cedula, @RequestBody Empleado empleado){
        return this.empleadoService.updateEmployee(cedula, empleado);
    }
    @PreAuthorize("hasRole('WRITE')")
    @DeleteMapping("/empleado/{cedula}")
    @ApiOperation(value = "Eliminar un empleado", response = Empleado.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Empleado fue actualizado exitosamente", response = Empleado.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Empleado.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Empleado.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Empleado.class)

    })
    public String deleteEmployee(@PathVariable Integer cedula){
        this.empleadoService.deleteEmployee(cedula);
        return "El empleado con cédula" + cedula + " fue eliminado con éxito";
    }
    @PreAuthorize("hasRole('READ')")
    @GetMapping("/empleado/{cedula}")
    @ApiOperation(value = "Obtener un empleado por cédula", response = Empleado.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Empleado se obtuvo exitosamente", response = Empleado.class),
            @ApiResponse(code = 400, message = "Solicitud incorrecta", response = Empleado.class),
            @ApiResponse(code = 401, message = "No autorizado", response = Empleado.class),
            @ApiResponse(code = 403, message = "Prohibido", response = Empleado.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado o cliente inexistente", response = Empleado.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = Empleado.class)

    })
    public Empleado getEmployeeByCedula(@PathVariable Integer cedula){
        return this.empleadoService.getEmployeeByCedula(cedula);
    }

}
