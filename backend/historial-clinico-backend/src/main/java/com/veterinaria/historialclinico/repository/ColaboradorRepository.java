package com.veterinaria.historialclinico.repository;

import com.veterinaria.historialclinico.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
}
