package com.veterinaria.historialclinico.service;

import com.veterinaria.historialclinico.dto.MascotaRequest;
import com.veterinaria.historialclinico.entity.Mascota;

import java.util.List;

public interface MascotaService {
    List<Mascota> findAll();
    Mascota findById(Long id);
    List<Mascota> findByUsuarioId(Long usuarioId);
    Mascota create(MascotaRequest request);
    Mascota update(Long id, MascotaRequest request);
    void delete(Long id);
}