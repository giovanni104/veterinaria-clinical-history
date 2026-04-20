package com.veterinaria.historialclinico.controller;

import com.veterinaria.historialclinico.dto.HistoriaClinicaRequest;
import com.veterinaria.historialclinico.entity.HistoriaClinica;
import com.veterinaria.historialclinico.service.HistoriaClinicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historias-clinicas")
@RequiredArgsConstructor
 
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;

    @GetMapping
    public List<HistoriaClinica> findAll() {
        return historiaClinicaService.findAll();
    }

    @GetMapping("/{id}")
    public HistoriaClinica findById(@PathVariable Long id) {
        return historiaClinicaService.findById(id);
    }

    @GetMapping("/mascota/{mascotaId}")
    public List<HistoriaClinica> findByMascota(@PathVariable Long mascotaId) {
        return historiaClinicaService.findByMascotaId(mascotaId);
    }

    @PostMapping
    public HistoriaClinica create(@Valid @RequestBody HistoriaClinicaRequest request) {
        return historiaClinicaService.create(request);
    }

    @PutMapping("/{id}")
    public HistoriaClinica update(@PathVariable Long id,
                                  @Valid @RequestBody HistoriaClinicaRequest request) {
        return historiaClinicaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        historiaClinicaService.delete(id);
    }
}