package com.example.GestionDeEnviosLogisticosParcial.Model.DTO;

import com.example.GestionDeEnviosLogisticosParcial.Model.Enum.Estado;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class EnvioDTO {
    private LocalDate fechaEnvio;
    private Double pesoKg;
    private Estado estado;
    private Integer idCliente;
    private Integer idRecursoLogistico;
}
