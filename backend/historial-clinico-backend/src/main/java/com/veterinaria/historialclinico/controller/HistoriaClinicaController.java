package com.veterinaria.historialclinico.controller;

import com.veterinaria.historialclinico.dto.HistoriaClinicaRequest;
import com.veterinaria.historialclinico.entity.HistoriaClinica;
import com.veterinaria.historialclinico.service.HistoriaClinicaService;
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
@RequestMapping("/api/historias-clinicas")
@RequiredArgsConstructor
@Tag(name = "Historias Clínicas", description = "API para la gestión de historias clínicas")
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;

    @Operation(summary = "Listar todas las historias clínicas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de historias clínicas obtenida correctamente")
    })
    @GetMapping
    public List<HistoriaClinica> findAll() {
        return historiaClinicaService.findAll();
    }

    @Operation(summary = "Obtener historia clínica por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historia clínica encontrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Historia clínica no encontrada")
    })
    @GetMapping("/{id}")
    public HistoriaClinica findById(@Parameter(description = "ID de la historia clínica", example = "1")
                                    @PathVariable Long id) {
        return historiaClinicaService.findById(id);
    }

    @Operation(summary = "Listar historias clínicas por mascota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de historias clínicas obtenida correctamente")
    })
    @GetMapping("/mascota/{mascotaId}")
    public List<HistoriaClinica> findByMascota(@Parameter(description = "ID de la mascota", example = "1")
                                               @PathVariable Long mascotaId) {
        return historiaClinicaService.findByMascotaId(mascotaId);
    }

    @Operation(summary = "Crear una nueva historia clínica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historia clínica creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @PostMapping
    public HistoriaClinica create(@Valid @RequestBody HistoriaClinicaRequest request) {
        return historiaClinicaService.create(request);
    }

    @Operation(summary = "Actualizar una historia clínica existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historia clínica actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Historia clínica o mascota no encontrada")
    })
    @PutMapping("/{id}")
    public HistoriaClinica update(@Parameter(description = "ID de la historia clínica", example = "1")
                                  @PathVariable Long id,
                                  @Valid @RequestBody HistoriaClinicaRequest request) {
        return historiaClinicaService.update(id, request);
    }

    @Operation(summary = "Eliminar una historia clínica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historia clínica eliminada correctamente"),
            @ApiResponse(responseCode = "400", description = "No se puede eliminar porque tiene detalles asociados"),
            @ApiResponse(responseCode = "404", description = "Historia clínica no encontrada")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID de la historia clínica", example = "1")
                       @PathVariable Long id) {
        historiaClinicaService.delete(id);
    }
}