package com.veterinaria.historialclinico.service;

import com.veterinaria.historialclinico.dto.HistoriaClinicaRequest;
import com.veterinaria.historialclinico.entity.HistoriaClinica;

import java.util.List;

public interface HistoriaClinicaService {
    List<HistoriaClinica> findAll();
    HistoriaClinica findById(Long id);
    List<HistoriaClinica> findByMascotaId(Long mascotaId);
    HistoriaClinica create(HistoriaClinicaRequest request);
    HistoriaClinica update(Long id, HistoriaClinicaRequest request);
    void delete(Long id);
}