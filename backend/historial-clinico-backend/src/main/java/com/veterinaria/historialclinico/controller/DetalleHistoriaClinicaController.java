package com.veterinaria.historialclinico.controller;

import com.veterinaria.historialclinico.dto.DetalleHistoriaClinicaRequest;
import com.veterinaria.historialclinico.entity.DetalleHistoriaClinica;
import com.veterinaria.historialclinico.service.DetalleHistoriaClinicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-historia-clinica")
@RequiredArgsConstructor
 
public class DetalleHistoriaClinicaController {

    private final DetalleHistoriaClinicaService detalleHistoriaClinicaService;

    @GetMapping
    public List<DetalleHistoriaClinica> findAll() {
        return detalleHistoriaClinicaService.findAll();
    }

    @GetMapping("/{id}")
    public DetalleHistoriaClinica findById(@PathVariable Long id) {
        return detalleHistoriaClinicaService.findById(id);
    }

    @GetMapping("/historia/{historiaClinicaId}")
    public List<DetalleHistoriaClinica> findByHistoriaClinica(@PathVariable Long historiaClinicaId) {
        return detalleHistoriaClinicaService.findByHistoriaClinicaId(historiaClinicaId);
    }

    @PostMapping
    public DetalleHistoriaClinica create(@Valid @RequestBody DetalleHistoriaClinicaRequest request) {
        return detalleHistoriaClinicaService.create(request);
    }

    @PutMapping("/{id}")
    public DetalleHistoriaClinica update(@PathVariable Long id,
                                         @Valid @RequestBody DetalleHistoriaClinicaRequest request) {
        return detalleHistoriaClinicaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        detalleHistoriaClinicaService.delete(id);
    }
}