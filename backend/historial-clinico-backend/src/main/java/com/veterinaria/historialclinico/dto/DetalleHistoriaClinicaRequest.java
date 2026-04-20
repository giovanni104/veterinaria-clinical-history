package com.veterinaria.historialclinico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DetalleHistoriaClinicaRequest {

    @NotNull
    @Positive
    private BigDecimal temperatura;

    @NotNull
    @Positive
    private BigDecimal peso;

    @NotNull
    @Positive
    private BigDecimal frecuenciaCardiaca;

    @NotNull
    @Positive
    private BigDecimal frecuenciaRespiratoria;

    @NotNull
    private LocalDateTime fechaHora;

    @NotBlank
    private String alimentacion;

    @NotBlank
    private String hidratacion;

    @NotBlank
    private String observacion;

    @NotNull
    private Long historiaClinicaId;

    @NotNull
    private Long colaboradorId;
}
