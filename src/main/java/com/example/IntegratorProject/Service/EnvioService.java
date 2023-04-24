package com.example.IntegratorProject.Service;

import com.example.IntegratorProject.DTO.*;
import com.example.IntegratorProject.Entities.Cliente;
import com.example.IntegratorProject.Entities.Empleado;
import com.example.IntegratorProject.Entities.Envio;
import com.example.IntegratorProject.Entities.Paquete;
import com.example.IntegratorProject.Repository.ClienteRepository;
import com.example.IntegratorProject.Repository.EmpleadoRepository;
import com.example.IntegratorProject.Repository.EnvioRepository;
import com.example.IntegratorProject.Repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {
    private EnvioRepository envioRepository;
    private ClienteRepository clienteRepository;
    private EmpleadoRepository empleadoRepository;
    private PaqueteRepository paqueteRepository;


    @Autowired
    public EnvioService(EnvioRepository envioRepository, ClienteRepository clienteRepository, EmpleadoRepository empleadoRepository, PaqueteRepository paqueteRepository) {
        this.envioRepository = envioRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.paqueteRepository = paqueteRepository;
    }

    public EnvioDTO createShipment(EnvioDtoRequest envioDtoRequest){
        //validamos el ingreso de todos los datos necesarios
        if(envioDtoRequest.getCedulaCliente() == null || envioDtoRequest.getNombreRecibe()== null ||  envioDtoRequest.getCiudadOrigen()==null
        || envioDtoRequest.getCiudadDestino()== null  || envioDtoRequest.getValorDeclarado() == 0
                || envioDtoRequest.getPeso() == 0 || envioDtoRequest.getDirDestino() == null){
            throw new RuntimeException("Hace falta informacion para realizar el envio");
        }
        //Verificamos que el cliente este registrado
        Optional<Cliente> optionalCliente = clienteRepository.findById(envioDtoRequest.getCedulaCliente());
        if(optionalCliente.isPresent()){
            Paquete paquete = new Paquete(Paquete.tipoDePaquete(envioDtoRequest.getPeso()), envioDtoRequest.getPeso(), envioDtoRequest.getValorDeclarado());
            this.paqueteRepository.save(paquete);
            Envio envio = new Envio(optionalCliente.get(), envioDtoRequest.getCiudadOrigen(), envioDtoRequest.getCiudadDestino(),
                    envioDtoRequest.getDirDestino(), envioDtoRequest.getNombreRecibe(), envioDtoRequest.getCelularRecibe(), horaEntrega(),"RECIBIDO", Envio.calcularEnvio(paquete.getTipoPaquete()), paquete);
            Envio envio1 = this.envioRepository.save(envio);
            envio1.setNumeroGuia(envio1.getNumeroGuia());
            EnvioDTO envioDTO = new EnvioDTO(envio1.getNumeroGuia(), envio1.getEstadoDelEnvio());
            return envioDTO;
        }else{
            throw new RuntimeException("El cliente con cedula" + envioDtoRequest.getCedulaCliente() + "debe estar registrado para poder enviar un paquete");
        }
    }


    public String horaEntrega(){
        DateFormat dateTimeFormatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateTimeFormatter.format(date);
    }

    public EnvioDtoInfo getInfoShipment(Integer numeroGuia){
        if(numeroGuia==null){
            throw new RuntimeException("El cliente debe estar registrado previamente");}
        Optional<Envio> optionalEnvio = envioRepository.findById(numeroGuia);
        if(optionalEnvio.isPresent()){
            Envio envio = optionalEnvio.get();
            EnvioDtoInfo envioDtoInfo = new EnvioDtoInfo();
            envioDtoInfo.setCedulaCliente(envio.getCliente().getCedula());
            envioDtoInfo.setNombreRemitente(envio.getCliente().getNombre());
            envioDtoInfo.setCiudadOrigen(envio.getCiudadOrigen());
            envioDtoInfo.setCiudadDestino(envio.getCiudadDestino());
            envioDtoInfo.setDireccionDestino(envio.getDireccionDestino());
            envioDtoInfo.setNombreRecibe(envio.getNombreDestinatario());
            envioDtoInfo.setCelular(envio.getNumeroCelular());
            envioDtoInfo.setValorDeclaradoPaquete(envio.getPaquete().getValorDeclarado());
            envioDtoInfo.setPeso(envio.getPaquete().getPeso());
            envioDtoInfo.setValorEnvio(envio.getValorEnvio());
            envioDtoInfo.setEstadoEnvio(envio.getEstadoDelEnvio());

            return envioDtoInfo;
        }
        else{
            throw new RuntimeException("No se encontró un envió con el número de guía: " + numeroGuia);
        }
    }

    public EnvioUpdateReturnDto updatePackageStatus (EnvioDtoUpdate envioDtoUpdate) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(envioDtoUpdate.getCedulaEmpleado());
        Optional<Envio> optionalEnvio = envioRepository.findById(envioDtoUpdate.getNumeroGuia());

        if (!optionalEmpleado.isPresent()) {
            throw new RuntimeException("El empleado con cedula: " + envioDtoUpdate.getCedulaEmpleado() + " no existe en nuestra compañía");
        }
        if (!optionalEnvio.isPresent()) {
            throw new RuntimeException("El numero de guia " + envioDtoUpdate.getNumeroGuia() + "no existe");
        }
        Empleado empleado = optionalEmpleado.get();
        Envio envio = optionalEnvio.get();
        if (empleado.getTipoEmpleado().equals("REPARTIDOR") || empleado.getTipoEmpleado().equals("COORDINADOR")) {
            if (envio.getEstadoDelEnvio().equals("RECIBIDO") && envioDtoUpdate.getEstadoEnvio().equals("EN RUTA")) {
                envio.setEstadoDelEnvio("EN RUTA");
                this.envioRepository.save(envio);
            } else if (envio.getEstadoDelEnvio().equals("EN RUTA") && envioDtoUpdate.getEstadoEnvio().equals("ENTREGADO")) {
                envio.setEstadoDelEnvio("ENTREGADO");
                this.envioRepository.save(envio);
            } else {
                throw new RuntimeException("El cambio de estado no cumple las validaciones");
            }
        } else {
            throw new RuntimeException("Solo los empleados de tipo REPARTIDOR y COORDINADOR pueden cambiar el estado del paquete");
        }
        EnvioUpdateReturnDto envioUpdateReturnDto = new EnvioUpdateReturnDto();
        envioUpdateReturnDto.setNumeroGuia(envio.getNumeroGuia());
        envioUpdateReturnDto.setUltimoEstado(envio.getEstadoDelEnvio());
        return envioUpdateReturnDto;
    }

    public List<Envio> getEnviosByStatusAndIdEmployee(String estadoDelEnvio, Integer cedula){
        Optional<Empleado> empleado = this.empleadoRepository.findById(cedula);
        if(!empleado.isPresent()){
            throw new RuntimeException("La empleado con cedula " + cedula + " no existe en nuestra compañía");
        }
        return this.envioRepository.filtroPorEstado(estadoDelEnvio);
    }








}
