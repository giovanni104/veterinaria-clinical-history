package com.veterinaria.historialclinico.controller;

import com.veterinaria.historialclinico.dto.MascotaRequest;
import com.veterinaria.historialclinico.entity.Mascota;
import com.veterinaria.historialclinico.service.MascotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
 
public class MascotaController {

    private final MascotaService mascotaService;

    @GetMapping
    public List<Mascota> findAll() {
        return mascotaService.findAll();
    }

    @GetMapping("/{id}")
    public Mascota findById(@PathVariable Long id) {
        return mascotaService.findById(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Mascota> findByUsuario(@PathVariable Long usuarioId) {
        return mascotaService.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public Mascota create(@Valid @RequestBody MascotaRequest request) {
        return mascotaService.create(request);
    }

    @PutMapping("/{id}")
    public Mascota update(@PathVariable Long id, @Valid @RequestBody MascotaRequest request) {
        return mascotaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mascotaService.delete(id);
    }
}