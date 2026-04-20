package com.veterinaria.historialclinico.service;

import com.veterinaria.historialclinico.dto.DetalleHistoriaClinicaRequest;
import com.veterinaria.historialclinico.entity.DetalleHistoriaClinica;

import java.util.List;

public interface DetalleHistoriaClinicaService {
    List<DetalleHistoriaClinica> findAll();
    DetalleHistoriaClinica findById(Long id);
    List<DetalleHistoriaClinica> findByHistoriaClinicaId(Long historiaClinicaId);
    DetalleHistoriaClinica create(DetalleHistoriaClinicaRequest request);
    DetalleHistoriaClinica update(Long id, DetalleHistoriaClinicaRequest request);
    void delete(Long id);
}