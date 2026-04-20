package com.veterinaria.historialclinico.service.impl;

import com.veterinaria.historialclinico.dto.UsuarioRequest;
import com.veterinaria.historialclinico.entity.Usuario;
import com.veterinaria.historialclinico.exception.BusinessException;
import com.veterinaria.historialclinico.exception.ResourceNotFoundException;
import com.veterinaria.historialclinico.repository.MascotaRepository;
import com.veterinaria.historialclinico.repository.UsuarioRepository;
import com.veterinaria.historialclinico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final MascotaRepository mascotaRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }

    @Override
    public Usuario create(UsuarioRequest request) {
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .correo(request.getCorreo())
                .tipoDocumento(request.getTipoDocumento())
                .documentoIdentificacion(request.getDocumentoIdentificacion())
                .estado(request.getEstado())
                .sexo(request.getSexo())
                .build();

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Long id, UsuarioRequest request) {
        Usuario usuario = findById(id);
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setTipoDocumento(request.getTipoDocumento());
        usuario.setDocumentoIdentificacion(request.getDocumentoIdentificacion());
        usuario.setEstado(request.getEstado());
        usuario.setSexo(request.getSexo());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) {
        Usuario usuario = findById(id);

        if (mascotaRepository.existsByUsuarioId(id)) {
            throw new BusinessException("No se puede eliminar el usuario porque tiene mascotas asociadas");
        }

        usuarioRepository.delete(usuario);
    }
}