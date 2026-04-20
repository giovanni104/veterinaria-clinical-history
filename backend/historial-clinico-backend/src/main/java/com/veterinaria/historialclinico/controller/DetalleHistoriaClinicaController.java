package com.veterinaria.historialclinico.controller;

import com.veterinaria.historialclinico.dto.DetalleHistoriaClinicaRequest;
import com.veterinaria.historialclinico.entity.DetalleHistoriaClinica;
import com.veterinaria.historialclinico.service.DetalleHistoriaClinicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-historia-clinica")
@RequiredArgsConstructor
@Tag(name = "Detalles Historia Clínica", description = "API para la gestión de detalles de historias clínicas")
public class DetalleHistoriaClinicaController {

    private final DetalleHistoriaClinicaService detalleHistoriaClinicaService;

    @Operation(summary = "Listar todos los detalles de historia clínica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de detalles obtenida correctamente")
    })
    @GetMapping
    public List<DetalleHistoriaClinica> findAll() {
        return detalleHistoriaClinicaService.findAll();
    }

    @Operation(summary = "Obtener detalle de historia clínica por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle encontrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    @GetMapping("/{id}")
    public DetalleHistoriaClinica findById(@Parameter(description = "ID del detalle clínico", example = "1")
                                           @PathVariable Long id) {
        return detalleHistoriaClinicaService.findById(id);
    }

    @Operation(summary = "Listar detalles por historia clínica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de detalles obtenida correctamente")
    })
    @GetMapping("/historia/{historiaClinicaId}")
    public List<DetalleHistoriaClinica> findByHistoriaClinica(
            @Parameter(description = "ID de la historia clínica", example = "1")
            @PathVariable Long historiaClinicaId) {
        return detalleHistoriaClinicaService.findByHistoriaClinicaId(historiaClinicaId);
    }

    @Operation(summary = "Crear un nuevo detalle de historia clínica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Historia clínica o colaborador no encontrado")
    })
    @PostMapping
    public DetalleHistoriaClinica create(@Valid @RequestBody DetalleHistoriaClinicaRequest request) {
        return detalleHistoriaClinicaService.create(request);
    }

    @Operation(summary = "Actualizar un detalle de historia clínica existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Detalle, historia clínica o colaborador no encontrado")
    })
    @PutMapping("/{id}")
    public DetalleHistoriaClinica update(@Parameter(description = "ID del detalle clínico", example = "1")
                                         @PathVariable Long id,
                                         @Valid @RequestBody DetalleHistoriaClinicaRequest request) {
        return detalleHistoriaClinicaService.update(id, request);
    }

    @Operation(summary = "Eliminar un detalle de historia clínica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID del detalle clínico", example = "1")
                       @PathVariable Long id) {
        detalleHistoriaClinicaService.delete(id);
    }
}