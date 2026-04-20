package com.veterinaria.historialclinico.service;

import com.veterinaria.historialclinico.dto.UsuarioRequest;
import com.veterinaria.historialclinico.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario create(UsuarioRequest request);
    Usuario update(Long id, UsuarioRequest request);
    void delete(Long id);
}
