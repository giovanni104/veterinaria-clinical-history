package com.veterinaria.historialclinico.repository;

import com.veterinaria.historialclinico.entity.DetalleHistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleHistoriaClinicaRepository extends JpaRepository<DetalleHistoriaClinica, Long> {
    List<DetalleHistoriaClinica> findByHistoriaClinicaId(Long historiaClinicaId);
    boolean existsByHistoriaClinicaId(Long historiaClinicaId);
    boolean existsByColaboradorId(Long colaboradorId);
}