package com.example.GestionDeEnviosLogisticosParcial.Service;

import com.example.GestionDeEnviosLogisticosParcial.Model.RecursoLogistico;
import com.example.GestionDeEnviosLogisticosParcial.Repository.RecursoLogisticoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecursoLogisticoService {
    private RecursoLogisticoRepository recursoLogisticoRepository;

    public RecursoLogisticoService(RecursoLogisticoRepository recursoLogisticoRepository) {
        this.recursoLogisticoRepository = recursoLogisticoRepository;
    }

    public RecursoLogistico altaRecursoLogistico (RecursoLogistico recursoLogistico)
    {
        return recursoLogisticoRepository.save(recursoLogistico);
    }

    public void bajaRecursoLogistico (Integer id)
    {
        recursoLogisticoRepository.deleteById(id);
    }

    public RecursoLogistico updateRecursoLogistico (RecursoLogistico recursoLogistico, Integer id)
    {
      RecursoLogistico recursoAmodificar = recursoLogisticoRepository.findById(id).orElseThrow();
      recursoAmodificar.setNombre(recursoLogistico.getNombre());
      recursoAmodificar.setCantidadDisponible(recursoLogistico.getCantidadDisponible());
      recursoAmodificar.setTipoVehiculo(recursoLogistico.getTipoVehiculo());
      recursoAmodificar.setTipoCargaPrincipal(recursoLogistico.getTipoCargaPrincipal());
      recursoAmodificar.setTipoCargaSecundaria(recursoLogistico.getTipoCargaSecundaria());
      recursoAmodificar.setCapacidadMaximaKg(recursoLogistico.getCapacidadMaximaKg());

      return recursoLogisticoRepository.save(recursoAmodificar);
    }

    public List<RecursoLogistico> mostrarListaRecursosLogisticos ()
    {
        return recursoLogisticoRepository.findAll();
    }

    public List<RecursoLogistico> mostrarRecursosLogisticosXtipoDeCarga(String tipoCarga)
    {
        return recursoLogisticoRepository.findAll().stream()
                .filter(r->r.getTipoCargaPrincipal().equalsIgnoreCase(tipoCarga)).toList();

    }
}
