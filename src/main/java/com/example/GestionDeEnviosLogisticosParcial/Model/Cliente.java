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
@Table(name="cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nombre_completo")
    private String nombreCompleto;
    private String ciudad;
    private String telefono;
    @Column(name="tipo_cliente")
    private String tipoCliente;
}
