package com.veterinaria.historialclinico.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ColaboradorRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    private String cargo;

    @NotBlank
    private String especialidad;

    @NotBlank
    private String tipoDocumento;

    @NotBlank
    private String documentoIdentificacion;
}