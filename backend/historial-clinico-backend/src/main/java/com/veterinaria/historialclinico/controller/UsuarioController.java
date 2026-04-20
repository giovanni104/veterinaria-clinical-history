package com.veterinaria.historialclinico.controller;

import com.veterinaria.historialclinico.dto.UsuarioRequest;
import com.veterinaria.historialclinico.entity.Usuario;
import com.veterinaria.historialclinico.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
 
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping
    public Usuario create(@Valid @RequestBody UsuarioRequest request) {
        return usuarioService.create(request);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id, @Valid @RequestBody UsuarioRequest request) {
        return usuarioService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}