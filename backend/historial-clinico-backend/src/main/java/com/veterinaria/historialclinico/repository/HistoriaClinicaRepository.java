package com.veterinaria.historialclinico.repository;

import com.veterinaria.historialclinico.entity.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {
    List<HistoriaClinica> findByMascotaId(Long mascotaId);
    boolean existsByMascotaId(Long mascotaId);
}