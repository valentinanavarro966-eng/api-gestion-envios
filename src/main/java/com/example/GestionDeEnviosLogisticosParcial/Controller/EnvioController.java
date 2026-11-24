package com.example.GestionDeEnviosLogisticosParcial.Controller;

import com.example.GestionDeEnviosLogisticosParcial.Model.DTO.EnvioDTO;
import com.example.GestionDeEnviosLogisticosParcial.Model.Envio;
import com.example.GestionDeEnviosLogisticosParcial.Model.Exception.EnvioInvalidoException;
import com.example.GestionDeEnviosLogisticosParcial.Service.EnvioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/envios")
public class EnvioController {
    private EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping("/nuevoEnvio")
    public Envio crearEnvio (@RequestBody EnvioDTO dto) throws EnvioInvalidoException
    {
        return envioService.altaEnvio(dto);
    }

    @PutMapping("/modificarEstadoAfinalizado/{id}")
    public Envio modificarEstadoFinalizado (@PathVariable Integer id, @RequestBody EnvioDTO dto) throws EnvioInvalidoException
    {
        return envioService.modificarEstado(id, dto);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Envio> mostrarEnviosDeCliente (@PathVariable Integer idCliente)
    {
        return envioService.listarEnvioDeCliente(idCliente);
    }
}
