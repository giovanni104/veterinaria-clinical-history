package com.veterinaria.historialclinico.controller;

import com.veterinaria.historialclinico.dto.MascotaRequest;
import com.veterinaria.historialclinico.entity.Mascota;
import com.veterinaria.historialclinico.service.MascotaService;
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
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
@Tag(name = "Mascotas", description = "API para la gestión de mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    @Operation(summary = "Listar todas las mascotas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de mascotas obtenida correctamente")
    })
    @GetMapping
    public List<Mascota> findAll() {
        return mascotaService.findAll();
    }

    @Operation(summary = "Obtener mascota por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mascota encontrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @GetMapping("/{id}")
    public Mascota findById(@Parameter(description = "ID de la mascota", example = "1")
                            @PathVariable Long id) {
        return mascotaService.findById(id);
    }

    @Operation(summary = "Listar mascotas por usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de mascotas obtenida correctamente")
    })
    @GetMapping("/usuario/{usuarioId}")
    public List<Mascota> findByUsuario(@Parameter(description = "ID del usuario dueño", example = "1")
                                       @PathVariable Long usuarioId) {
        return mascotaService.findByUsuarioId(usuarioId);
    }

    @Operation(summary = "Crear una nueva mascota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mascota creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PostMapping
    public Mascota create(@Valid @RequestBody MascotaRequest request) {
        return mascotaService.create(request);
    }

    @Operation(summary = "Actualizar una mascota existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mascota actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Mascota o usuario no encontrado")
    })
    @PutMapping("/{id}")
    public Mascota update(@Parameter(description = "ID de la mascota", example = "1")
                          @PathVariable Long id,
                          @Valid @RequestBody MascotaRequest request) {
        return mascotaService.update(id, request);
    }

    @Operation(summary = "Eliminar una mascota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mascota eliminada correctamente"),
            @ApiResponse(responseCode = "400", description = "No se puede eliminar porque tiene historias clínicas asociadas"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID de la mascota", example = "1")
                       @PathVariable Long id) {
        mascotaService.delete(id);
    }
}