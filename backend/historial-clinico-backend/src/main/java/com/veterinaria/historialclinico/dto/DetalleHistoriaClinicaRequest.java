package com.veterinaria.historialclinico.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "DTO para la creación y actualización del detalle de una historia clínica")
public class DetalleHistoriaClinicaRequest {

    @Schema(description = "Temperatura del paciente", example = "38.50")
    @NotNull
    @Positive
    private BigDecimal temperatura;

    @Schema(description = "Peso del paciente", example = "12.80")
    @NotNull
    @Positive
    private BigDecimal peso;

    @Schema(description = "Frecuencia cardíaca del paciente", example = "95.00")
    @NotNull
    @Positive
    private BigDecimal frecuenciaCardiaca;

    @Schema(description = "Frecuencia respiratoria del paciente", example = "24.00")
    @NotNull
    @Positive
    private BigDecimal frecuenciaRespiratoria;

    @Schema(description = "Fecha y hora del registro clínico", example = "2026-04-19T10:30:00")
    @NotNull
    private LocalDateTime fechaHora;

    @Schema(description = "Estado de alimentación del paciente", example = "Buena")
    @NotBlank
    private String alimentacion;

    @Schema(description = "Estado de hidratación del paciente", example = "Adecuada")
    @NotBlank
    private String hidratacion;

    @Schema(description = "Observaciones del registro clínico", example = "Paciente estable, sin signos de alarma.")
    @NotBlank
    private String observacion;

    @Schema(description = "ID de la historia clínica asociada", example = "1")
    @NotNull
    private Long historiaClinicaId;

    @Schema(description = "ID del colaborador que registra el detalle clínico", example = "1")
    @NotNull
    private Long colaboradorId;
}