package com.example.GestionDeEnviosLogisticosParcial.Service;
import com.example.GestionDeEnviosLogisticosParcial.Model.Cliente;
import com.example.GestionDeEnviosLogisticosParcial.Model.DTO.EnvioDTO;
import com.example.GestionDeEnviosLogisticosParcial.Model.Enum.Estado;
import com.example.GestionDeEnviosLogisticosParcial.Model.Envio;
import com.example.GestionDeEnviosLogisticosParcial.Model.Exception.EnvioInvalidoException;
import com.example.GestionDeEnviosLogisticosParcial.Model.RecursoLogistico;
import com.example.GestionDeEnviosLogisticosParcial.Repository.ClienteRepository;
import com.example.GestionDeEnviosLogisticosParcial.Repository.EnvioRepository;
import com.example.GestionDeEnviosLogisticosParcial.Repository.RecursoLogisticoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnvioService {
    private EnvioRepository envioRepository;
    private ClienteRepository clienteRepository;
    private RecursoLogisticoRepository recursoLogisticoRepository;

    public EnvioService(EnvioRepository envioRepository, ClienteRepository clienteRepository, RecursoLogisticoRepository recursoLogisticoRepository) {
        this.envioRepository = envioRepository;
        this.clienteRepository = clienteRepository;
        this.recursoLogisticoRepository = recursoLogisticoRepository;
    }

    public Envio altaEnvio (EnvioDTO dto) throws EnvioInvalidoException
    {

        if(!clienteRepository.existsById(dto.getIdCliente()))
        {
            throw new EnvioInvalidoException("ERROR, cliente inexistente.");
        }

        if (!recursoLogisticoRepository.existsById(dto.getIdRecursoLogistico()))
        {
            throw  new EnvioInvalidoException("ERROR, recurso logistico inexistente.");
        }

        Cliente cliente= clienteRepository.findById(dto.getIdCliente()).orElseThrow();
        RecursoLogistico recursoLogistico= recursoLogisticoRepository.findById(dto.getIdRecursoLogistico()).orElseThrow();

        if (recursoLogistico.getCantidadDisponible()<1)
        {
            throw  new EnvioInvalidoException("ERROR, no hay cantidad de recursos disponibles.");
        }

        if (dto.getFechaEnvio().isBefore(LocalDate.now()))
        {
            throw  new EnvioInvalidoException("ERROR, la fecha de envio no puede ser antes que la actual.");
        }

        if(dto.getPesoKg()>recursoLogistico.getCapacidadMaximaKg())
        {
            throw  new EnvioInvalidoException("ERROR, el peso del envio supera la cantidad maxima del recurso logistico");
        }
        Envio envio= new Envio();
        envio.setFechaEnvio(dto.getFechaEnvio());
        envio.setPesoKg(dto.getPesoKg());
        envio.setEstado(Estado.ACTIVO);
        envio.setCliente(cliente);
        envio.setRecursoLogistico(recursoLogistico);

        int nuevaCantidadRecursoLogistico= recursoLogistico.getCantidadDisponible()-1;
        recursoLogistico.setCantidadDisponible(nuevaCantidadRecursoLogistico);

        return envioRepository.save(envio);
    }

    public Envio modificarEstado (Integer id, EnvioDTO dto) throws EnvioInvalidoException
    {
        if (!envioRepository.existsById(id))
        {
            throw new EnvioInvalidoException("ERROR, envio inexistente.");
        }

        Envio envio= envioRepository.findById(id).orElseThrow();

        if (!envio.getEstado().equals(Estado.ACTIVO))
        {
            throw new EnvioInvalidoException("ERROR, el envio ya se encuentra en estado FINALIZADO.");
        }

        envio.setEstado(Estado.FINALIZADO);

        RecursoLogistico recursoLogistico = recursoLogisticoRepository.findById(dto.getIdRecursoLogistico()).orElseThrow();

        int nuevaCantidad = recursoLogistico.getCantidadDisponible()+1;
        recursoLogistico.setCantidadDisponible(nuevaCantidad);

        return envioRepository.save(envio);
    }

    public List<Envio> listarEnvioDeCliente (Integer idCliente)
    {
        return envioRepository.findByClienteId(idCliente);
    }
}
