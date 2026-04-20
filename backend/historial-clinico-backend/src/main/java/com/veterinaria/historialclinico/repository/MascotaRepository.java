package com.veterinaria.historialclinico.repository;

import com.veterinaria.historialclinico.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioId(Long usuarioId);
}