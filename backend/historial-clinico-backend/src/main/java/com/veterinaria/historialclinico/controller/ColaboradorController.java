package com.veterinaria.historialclinico.controller;

import com.veterinaria.historialclinico.dto.ColaboradorRequest;
import com.veterinaria.historialclinico.entity.Colaborador;
import com.veterinaria.historialclinico.service.ColaboradorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colaboradores")
@RequiredArgsConstructor
 
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    @GetMapping
    public List<Colaborador> findAll() {
        return colaboradorService.findAll();
    }

    @GetMapping("/{id}")
    public Colaborador findById(@PathVariable Long id) {
        return colaboradorService.findById(id);
    }

    @PostMapping
    public Colaborador create(@Valid @RequestBody ColaboradorRequest request) {
        return colaboradorService.create(request);
    }

    @PutMapping("/{id}")
    public Colaborador update(@PathVariable Long id,
                              @Valid @RequestBody ColaboradorRequest request) {
        return colaboradorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        colaboradorService.delete(id);
    }
}