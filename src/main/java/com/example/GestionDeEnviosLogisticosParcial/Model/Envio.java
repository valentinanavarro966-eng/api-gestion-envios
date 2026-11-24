package com.example.GestionDeEnviosLogisticosParcial.Model;

import com.example.GestionDeEnviosLogisticosParcial.Model.Enum.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="envio")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="fecha_envio")
    private LocalDate fechaEnvio;
    @Column(name="peso_kg")
    private Double pesoKg;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne
    @JoinColumn(name= "id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="id_recurso_logistico")
    private RecursoLogistico recursoLogistico;


}
