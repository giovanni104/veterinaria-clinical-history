package com.veterinaria.historialclinico.controller;

import com.veterinaria.historialclinico.dto.ColaboradorRequest;
import com.veterinaria.historialclinico.entity.Colaborador;
import com.veterinaria.historialclinico.service.ColaboradorService;
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
@RequestMapping("/api/colaboradores")
@RequiredArgsConstructor
@Tag(name = "Colaboradores", description = "API para la gestión de colaboradores")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    @Operation(summary = "Listar todos los colaboradores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de colaboradores obtenida correctamente")
    })
    @GetMapping
    public List<Colaborador> findAll() {
        return colaboradorService.findAll();
    }

    @Operation(summary = "Obtener colaborador por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador encontrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Colaborador no encontrado")
    })
    @GetMapping("/{id}")
    public Colaborador findById(@Parameter(description = "ID del colaborador", example = "1")
                                @PathVariable Long id) {
        return colaboradorService.findById(id);
    }

    @Operation(summary = "Crear un nuevo colaborador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public Colaborador create(@Valid @RequestBody ColaboradorRequest request) {
        return colaboradorService.create(request);
    }

    @Operation(summary = "Actualizar un colaborador existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Colaborador no encontrado")
    })
    @PutMapping("/{id}")
    public Colaborador update(@Parameter(description = "ID del colaborador", example = "1")
                              @PathVariable Long id,
                              @Valid @RequestBody ColaboradorRequest request) {
        return colaboradorService.update(id, request);
    }

    @Operation(summary = "Eliminar un colaborador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador eliminado correctamente"),
            @ApiResponse(responseCode = "400", description = "No se puede eliminar porque tiene detalles clínicos asociados"),
            @ApiResponse(responseCode = "404", description = "Colaborador no encontrado")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID del colaborador", example = "1")
                       @PathVariable Long id) {
        colaboradorService.delete(id);
    }
}