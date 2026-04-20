package com.veterinaria.historialclinico.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MascotaRequest {

    @Schema(description = "Nombre de la mascota", example = "Max")
    @NotBlank
    private String nombre;

    @Schema(description = "Raza de la mascota", example = "Labrador")
    @NotBlank
    private String raza;

    @Schema(description = "Vacuna aplicada", example = "Rabia")
    @NotBlank
    private String vacuna;

    @Schema(description = "Sexo de la mascota", example = "Macho")
    @NotBlank
    private String sexo;

    @Schema(description = "ID del usuario dueño", example = "1")
    @NotNull
    private Long usuarioId;
}