package com.example.GestionDeEnviosLogisticosParcial.Repository;

import com.example.GestionDeEnviosLogisticosParcial.Model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvioRepository extends JpaRepository<Envio, Integer> {
    List<Envio>findByClienteId (Integer id);
}
