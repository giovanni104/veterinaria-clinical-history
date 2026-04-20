package com.veterinaria.historialclinico.repository;

import com.veterinaria.historialclinico.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}