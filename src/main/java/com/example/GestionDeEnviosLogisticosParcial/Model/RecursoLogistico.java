package com.example.GestionDeEnviosLogisticosParcial.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="recurso_logistico")
public class RecursoLogistico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @Column(name="tipo_vehiculo")
    private String tipoVehiculo;
    @Column(name="tipo_carga_principal")
    private String tipoCargaPrincipal;
    @Column(name="tipo_carga_secundaria")
    private String tipoCargaSecundaria;
    @Column(name="capacidad_maxima_kg")
    private Double capacidadMaximaKg;
    @Column(name="cantidad_disponible")
    private int cantidadDisponible;





}
