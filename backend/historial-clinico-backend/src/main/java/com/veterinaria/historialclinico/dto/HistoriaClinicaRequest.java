package com.veterinaria.historialclinico.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoriaClinicaRequest {

    @NotNull
    private LocalDate fechaCreacion;

    @NotNull
    private Long mascotaId;
}