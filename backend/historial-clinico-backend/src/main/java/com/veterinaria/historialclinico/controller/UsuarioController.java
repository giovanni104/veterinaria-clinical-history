package com.veterinaria.historialclinico.controller;

import com.veterinaria.historialclinico.dto.UsuarioRequest;
import com.veterinaria.historialclinico.entity.Usuario;
import com.veterinaria.historialclinico.service.UsuarioService;
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
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "API para la gestión de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Listar todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente")
    })
    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @Operation(summary = "Obtener usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public Usuario findById(@Parameter(description = "ID del usuario", example = "1")
                            @PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public Usuario create(@Valid @RequestBody UsuarioRequest request) {
        return usuarioService.create(request);
    }

    @Operation(summary = "Actualizar un usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public Usuario update(@Parameter(description = "ID del usuario", example = "1")
                          @PathVariable Long id,
                          @Valid @RequestBody UsuarioRequest request) {
        return usuarioService.update(id, request);
    }

    @Operation(summary = "Eliminar un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "400", description = "No se puede eliminar porque tiene mascotas asociadas")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID del usuario", example = "1")
                       @PathVariable Long id) {
        usuarioService.delete(id);
    }
}