package com.example.GestionDeEnviosLogisticosParcial.Service;

import com.example.GestionDeEnviosLogisticosParcial.Model.Cliente;
import com.example.GestionDeEnviosLogisticosParcial.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes ()
    {
        return clienteRepository.findAll();
    }
}
