package com.veterinaria.historialclinico.service.impl;

import com.veterinaria.historialclinico.dto.MascotaRequest;
import com.veterinaria.historialclinico.entity.Mascota;
import com.veterinaria.historialclinico.entity.Usuario;
import com.veterinaria.historialclinico.exception.BusinessException;
import com.veterinaria.historialclinico.exception.ResourceNotFoundException;
import com.veterinaria.historialclinico.repository.HistoriaClinicaRepository;
import com.veterinaria.historialclinico.repository.MascotaRepository;
import com.veterinaria.historialclinico.repository.UsuarioRepository;
import com.veterinaria.historialclinico.service.MascotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota findById(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con id: " + id));
    }

    @Override
    public List<Mascota> findByUsuarioId(Long usuarioId) {
        return mascotaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public Mascota create(MascotaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + request.getUsuarioId()));

        Mascota mascota = Mascota.builder()
                .nombre(request.getNombre())
                .raza(request.getRaza())
                .vacuna(request.getVacuna())
                .sexo(request.getSexo())
                .usuario(usuario)
                .build();

        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota update(Long id, MascotaRequest request) {
        Mascota mascota = findById(id);

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + request.getUsuarioId()));

        mascota.setNombre(request.getNombre());
        mascota.setRaza(request.getRaza());
        mascota.setVacuna(request.getVacuna());
        mascota.setSexo(request.getSexo());
        mascota.setUsuario(usuario);

        return mascotaRepository.save(mascota);
    }

    @Override
    public void delete(Long id) {
        Mascota mascota = findById(id);

        if (historiaClinicaRepository.existsByMascotaId(id)) {
            throw new BusinessException("No se puede eliminar la mascota porque tiene historias clínicas asociadas");
        }

        mascotaRepository.delete(mascota);
    }
}