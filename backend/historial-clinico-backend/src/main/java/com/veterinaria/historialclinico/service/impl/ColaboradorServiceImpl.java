package com.veterinaria.historialclinico.service.impl;

import com.veterinaria.historialclinico.dto.ColaboradorRequest;
import com.veterinaria.historialclinico.entity.Colaborador;
import com.veterinaria.historialclinico.exception.BusinessException;
import com.veterinaria.historialclinico.exception.ResourceNotFoundException;
import com.veterinaria.historialclinico.repository.ColaboradorRepository;
import com.veterinaria.historialclinico.repository.DetalleHistoriaClinicaRepository;
import com.veterinaria.historialclinico.service.ColaboradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColaboradorServiceImpl implements ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final DetalleHistoriaClinicaRepository detalleHistoriaClinicaRepository;

    @Override
    public List<Colaborador> findAll() {
        return colaboradorRepository.findAll();
    }

    @Override
    public Colaborador findById(Long id) {
        return colaboradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colaborador no encontrado con id: " + id));
    }

    @Override
    public Colaborador create(ColaboradorRequest request) {
        Colaborador colaborador = Colaborador.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .cargo(request.getCargo())
                .especialidad(request.getEspecialidad())
                .tipoDocumento(request.getTipoDocumento())
                .documentoIdentificacion(request.getDocumentoIdentificacion())
                .build();

        return colaboradorRepository.save(colaborador);
    }

    @Override
    public Colaborador update(Long id, ColaboradorRequest request) {
        Colaborador colaborador = findById(id);
        colaborador.setNombre(request.getNombre());
        colaborador.setApellido(request.getApellido());
        colaborador.setCargo(request.getCargo());
        colaborador.setEspecialidad(request.getEspecialidad());
        colaborador.setTipoDocumento(request.getTipoDocumento());
        colaborador.setDocumentoIdentificacion(request.getDocumentoIdentificacion());
        return colaboradorRepository.save(colaborador);
    }

    @Override
    public void delete(Long id) {
        Colaborador colaborador = findById(id);

        if (detalleHistoriaClinicaRepository.existsByColaboradorId(id)) {
            throw new BusinessException("No se puede eliminar el colaborador porque tiene detalles clínicos asociados");
        }

        colaboradorRepository.delete(colaborador);
    }
}