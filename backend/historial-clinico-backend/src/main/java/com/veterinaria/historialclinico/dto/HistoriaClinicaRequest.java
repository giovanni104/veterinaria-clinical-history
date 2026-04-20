package com.veterinaria.historialclinico.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "DTO para la creación y actualización de historias clínicas")
public class HistoriaClinicaRequest {

    @Schema(description = "Fecha de creación de la historia clínica", example = "2026-04-19")
    @NotNull
    private LocalDate fechaCreacion;

    @Schema(description = "ID de la mascota asociada a la historia clínica", example = "1")
    @NotNull
    private Long mascotaId;
}