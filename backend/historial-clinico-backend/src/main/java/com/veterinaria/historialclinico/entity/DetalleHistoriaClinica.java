package com.veterinaria.historialclinico.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "detalles_historia_clinica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleHistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal temperatura;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal peso;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal frecuenciaCardiaca;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal frecuenciaRespiratoria;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private String alimentacion;

    @Column(nullable = false)
    private String hidratacion;

    @Column(nullable = false, length = 500)
    private String observacion;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "historia_clinica_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private HistoriaClinica historiaClinica;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "colaborador_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Colaborador colaborador;
}