package com.veterinaria.historialclinico.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "colaboradores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private String especialidad;

    @Column(nullable = false)
    private String tipoDocumento;

    @Column(nullable = false, unique = true)
    private String documentoIdentificacion;
}