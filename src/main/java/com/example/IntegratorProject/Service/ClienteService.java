package com.example.IntegratorProject.Service;

import com.example.IntegratorProject.Entities.Cliente;
import com.example.IntegratorProject.Entities.Empleado;
import com.example.IntegratorProject.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente createCustomer(Cliente cliente){
        return this.clienteRepository.save(cliente);

    }
    public Cliente updateCustomer(Integer cedula, Cliente cliente){
        Optional<Cliente> optionalCliente = clienteRepository.findById(cedula);
        if(optionalCliente.isPresent()){
            optionalCliente.get().setCedula(cliente.getCedula());
            optionalCliente.get().setNombre(cliente.getNombre());
            optionalCliente.get().setApellido(cliente.getApellido());
            optionalCliente.get().setCelular(cliente.getCelular());
            optionalCliente.get().setCorreoElectronico(cliente.getCorreoElectronico());
            optionalCliente.get().setDireccion(cliente.getDireccion());
            optionalCliente.get().setCiudad(cliente.getCiudad());
            return this.clienteRepository.save(optionalCliente.get());
        }else{
            throw new RuntimeException("El cliente con cedula " + cedula + "no fue encontrado");
        }
    }

    public void deleteCustomer(Integer cedula){
        Optional<Cliente> optionalCliente = clienteRepository.findById(cedula);
        if(optionalCliente.isPresent()){
            clienteRepository.delete(optionalCliente.get());
        }else{
            throw new RuntimeException("El cliente con cedula "+ cedula + "no fue posible encontrarlo");
        }
    }

    public Cliente getCustomerByCedula(Integer cedula){
        Optional<Cliente> optionalCliente = clienteRepository.findById(cedula);
        if(optionalCliente.isPresent()){
            return optionalCliente.get();
        }else{
            throw  new RuntimeException("El cliente con cedula "+ cedula + "no fue posible encontrarlo");
        }
    }



}
