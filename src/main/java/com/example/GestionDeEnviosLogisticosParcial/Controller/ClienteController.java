package com.example.GestionDeEnviosLogisticosParcial.Controller;

import com.example.GestionDeEnviosLogisticosParcial.Model.Cliente;
import com.example.GestionDeEnviosLogisticosParcial.Service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> mostrarClientes ()
    {
        return clienteService.listarClientes();
    }
}
