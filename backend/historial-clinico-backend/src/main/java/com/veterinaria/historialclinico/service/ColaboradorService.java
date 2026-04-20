package com.veterinaria.historialclinico.service;

import com.veterinaria.historialclinico.dto.ColaboradorRequest;
import com.veterinaria.historialclinico.entity.Colaborador;

import java.util.List;

public interface ColaboradorService {
    List<Colaborador> findAll();
    Colaborador findById(Long id);
    Colaborador create(ColaboradorRequest request);
    Colaborador update(Long id, ColaboradorRequest request);
    void delete(Long id);
}