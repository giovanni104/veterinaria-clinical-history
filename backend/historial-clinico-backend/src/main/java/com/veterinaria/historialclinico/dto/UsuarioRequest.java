package com.veterinaria.historialclinico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    @Email
    private String correo;

    @NotBlank
    private String tipoDocumento;

    @NotBlank
    private String documentoIdentificacion;

    @NotBlank
    private String estado;

    @NotBlank
    private String sexo;
}