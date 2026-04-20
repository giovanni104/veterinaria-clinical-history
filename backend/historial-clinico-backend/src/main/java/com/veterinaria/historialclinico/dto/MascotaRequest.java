package com.veterinaria.historialclinico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MascotaRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String raza;

    @NotBlank
    private String vacuna;

    @NotBlank
    private String sexo;

    @NotNull
    private Long usuarioId;
}
