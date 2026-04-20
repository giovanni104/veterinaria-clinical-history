package com.veterinaria.historialclinico.service.impl;

import com.veterinaria.historialclinico.dto.DetalleHistoriaClinicaRequest;
import com.veterinaria.historialclinico.entity.Colaborador;
import com.veterinaria.historialclinico.entity.DetalleHistoriaClinica;
import com.veterinaria.historialclinico.entity.HistoriaClinica;
import com.veterinaria.historialclinico.exception.ResourceNotFoundException;
import com.veterinaria.historialclinico.repository.ColaboradorRepository;
import com.veterinaria.historialclinico.repository.DetalleHistoriaClinicaRepository;
import com.veterinaria.historialclinico.repository.HistoriaClinicaRepository;
import com.veterinaria.historialclinico.service.DetalleHistoriaClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleHistoriaClinicaServiceImpl implements DetalleHistoriaClinicaService {

    private final DetalleHistoriaClinicaRepository detalleHistoriaClinicaRepository;
    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final ColaboradorRepository colaboradorRepository;

    @Override
    public List<DetalleHistoriaClinica> findAll() {
        return detalleHistoriaClinicaRepository.findAll();
    }

    @Override
    public DetalleHistoriaClinica findById(Long id) {
        return detalleHistoriaClinicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de historia clínica no encontrado con id: " + id));
    }

    @Override
    public List<DetalleHistoriaClinica> findByHistoriaClinicaId(Long historiaClinicaId) {
        return detalleHistoriaClinicaRepository.findByHistoriaClinicaId(historiaClinicaId);
    }

    @Override
    public DetalleHistoriaClinica create(DetalleHistoriaClinicaRequest request) {
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(request.getHistoriaClinicaId())
                .orElseThrow(() -> new ResourceNotFoundException("Historia clínica no encontrada con id: " + request.getHistoriaClinicaId()));

        Colaborador colaborador = colaboradorRepository.findById(request.getColaboradorId())
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador no encontrado con id: " + request.getColaboradorId()));

        DetalleHistoriaClinica detalle = DetalleHistoriaClinica.builder()
                .temperatura(request.getTemperatura())
                .peso(request.getPeso())
                .frecuenciaCardiaca(request.getFrecuenciaCardiaca())
                .frecuenciaRespiratoria(request.getFrecuenciaRespiratoria())
                .fechaHora(request.getFechaHora())
                .alimentacion(request.getAlimentacion())
                .hidratacion(request.getHidratacion())
                .observacion(request.getObservacion())
                .historiaClinica(historiaClinica)
                .colaborador(colaborador)
                .build();

        return detalleHistoriaClinicaRepository.save(detalle);
    }

    @Override
    public DetalleHistoriaClinica update(Long id, DetalleHistoriaClinicaRequest request) {
        DetalleHistoriaClinica detalle = findById(id);

        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(request.getHistoriaClinicaId())
                .orElseThrow(() -> new ResourceNotFoundException("Historia clínica no encontrada con id: " + request.getHistoriaClinicaId()));

        Colaborador colaborador = colaboradorRepository.findById(request.getColaboradorId())
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador no encontrado con id: " + request.getColaboradorId()));

        detalle.setTemperatura(request.getTemperatura());
        detalle.setPeso(request.getPeso());
        detalle.setFrecuenciaCardiaca(request.getFrecuenciaCardiaca());
        detalle.setFrecuenciaRespiratoria(request.getFrecuenciaRespiratoria());
        detalle.setFechaHora(request.getFechaHora());
        detalle.setAlimentacion(request.getAlimentacion());
        detalle.setHidratacion(request.getHidratacion());
        detalle.setObservacion(request.getObservacion());
        detalle.setHistoriaClinica(historiaClinica);
        detalle.setColaborador(colaborador);

        return detalleHistoriaClinicaRepository.save(detalle);
    }

    @Override
    public void delete(Long id) {
        DetalleHistoriaClinica detalle = findById(id);
        detalleHistoriaClinicaRepository.delete(detalle);
    }
}