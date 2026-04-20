package com.veterinaria.historialclinico.service.impl;

import com.veterinaria.historialclinico.dto.HistoriaClinicaRequest;
import com.veterinaria.historialclinico.entity.HistoriaClinica;
import com.veterinaria.historialclinico.entity.Mascota;
import com.veterinaria.historialclinico.exception.BusinessException;
import com.veterinaria.historialclinico.exception.ResourceNotFoundException;
import com.veterinaria.historialclinico.repository.DetalleHistoriaClinicaRepository;
import com.veterinaria.historialclinico.repository.HistoriaClinicaRepository;
import com.veterinaria.historialclinico.repository.MascotaRepository;
import com.veterinaria.historialclinico.service.HistoriaClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final MascotaRepository mascotaRepository;
    private final DetalleHistoriaClinicaRepository detalleHistoriaClinicaRepository;

    @Override
    public List<HistoriaClinica> findAll() {
        return historiaClinicaRepository.findAll();
    }

    @Override
    public HistoriaClinica findById(Long id) {
        return historiaClinicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historia clínica no encontrada con id: " + id));
    }

    @Override
    public List<HistoriaClinica> findByMascotaId(Long mascotaId) {
        return historiaClinicaRepository.findByMascotaId(mascotaId);
    }

    @Override
    public HistoriaClinica create(HistoriaClinicaRequest request) {
        Mascota mascota = mascotaRepository.findById(request.getMascotaId())
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con id: " + request.getMascotaId()));

        HistoriaClinica historiaClinica = HistoriaClinica.builder()
                .fechaCreacion(request.getFechaCreacion())
                .mascota(mascota)
                .build();

        return historiaClinicaRepository.save(historiaClinica);
    }

    @Override
    public HistoriaClinica update(Long id, HistoriaClinicaRequest request) {
        HistoriaClinica historiaClinica = findById(id);

        Mascota mascota = mascotaRepository.findById(request.getMascotaId())
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con id: " + request.getMascotaId()));

        historiaClinica.setFechaCreacion(request.getFechaCreacion());
        historiaClinica.setMascota(mascota);

        return historiaClinicaRepository.save(historiaClinica);
    }

    @Override
    public void delete(Long id) {
        HistoriaClinica historiaClinica = findById(id);

        if (detalleHistoriaClinicaRepository.existsByHistoriaClinicaId(id)) {
            throw new BusinessException("No se puede eliminar la historia clínica porque tiene detalles asociados");
        }

        historiaClinicaRepository.delete(historiaClinica);
    }
}