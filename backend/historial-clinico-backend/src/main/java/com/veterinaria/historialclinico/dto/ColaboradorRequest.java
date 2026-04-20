package com.veterinaria.historialclinico.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO para la creación y actualización de colaboradores")
public class ColaboradorRequest {

    @Schema(description = "Nombre del colaborador", example = "Ana")
    @NotBlank
    private String nombre;

    @Schema(description = "Apellido del colaborador", example = "Gomez")
    @NotBlank
    private String apellido;

    @Schema(description = "Cargo del colaborador", example = "Veterinaria")
    @NotBlank
    private String cargo;

    @Schema(description = "Especialidad del colaborador", example = "Medicina general")
    @NotBlank
    private String especialidad;

    @Schema(description = "Tipo de documento del colaborador", example = "CC")
    @NotBlank
    private String tipoDocumento;

    @Schema(description = "Documento de identificación del colaborador", example = "11111111")
    @NotBlank
    private String documentoIdentificacion;
}