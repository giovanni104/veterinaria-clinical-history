package com.veterinaria.historialclinico.service.impl;

import com.veterinaria.historialclinico.dto.DetalleHistoriaClinicaRequest;
import com.veterinaria.historialclinico.entity.Colaborador;
import com.veterinaria.historialclinico.entity.DetalleHistoriaClinica;
import com.veterinaria.historialclinico.entity.HistoriaClinica;
import com.veterinaria.historialclinico.exception.ResourceNotFoundException;
import com.veterinaria.historialclinico.repository.ColaboradorRepository;
import com.veterinaria.historialclinico.repository.DetalleHistoriaClinicaRepository;
import com.veterinaria.historialclinico.repository.HistoriaClinicaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DetalleHistoriaClinicaServiceImplTest {

    @Mock
    private DetalleHistoriaClinicaRepository detalleHistoriaClinicaRepository;

    @Mock
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @InjectMocks
    private DetalleHistoriaClinicaServiceImpl detalleHistoriaClinicaService;

    @Test
    void shouldFindAllDetallesHistoriaClinica() {
        HistoriaClinica historiaClinica = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .build();

        Colaborador colaborador = Colaborador.builder()
                .id(1L)
                .nombre("Ana")
                .apellido("Gomez")
                .build();

        DetalleHistoriaClinica detalle1 = DetalleHistoriaClinica.builder()
                .id(1L)
                .temperatura(new BigDecimal("38.50"))
                .peso(new BigDecimal("12.80"))
                .frecuenciaCardiaca(new BigDecimal("95.00"))
                .frecuenciaRespiratoria(new BigDecimal("24.00"))
                .fechaHora(LocalDateTime.of(2026, 4, 19, 10, 30))
                .alimentacion("Buena")
                .hidratacion("Adecuada")
                .observacion("Paciente estable")
                .historiaClinica(historiaClinica)
                .colaborador(colaborador)
                .build();

        DetalleHistoriaClinica detalle2 = DetalleHistoriaClinica.builder()
                .id(2L)
                .temperatura(new BigDecimal("39.00"))
                .peso(new BigDecimal("13.10"))
                .frecuenciaCardiaca(new BigDecimal("98.00"))
                .frecuenciaRespiratoria(new BigDecimal("25.00"))
                .fechaHora(LocalDateTime.of(2026, 4, 19, 11, 0))
                .alimentacion("Regular")
                .hidratacion("Adecuada")
                .observacion("Requiere observación")
                .historiaClinica(historiaClinica)
                .colaborador(colaborador)
                .build();

        when(detalleHistoriaClinicaRepository.findAll()).thenReturn(List.of(detalle1, detalle2));

        List<DetalleHistoriaClinica> result = detalleHistoriaClinicaService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(detalleHistoriaClinicaRepository).findAll();
    }

    @Test
    void shouldFindDetalleHistoriaClinicaById() {
        HistoriaClinica historiaClinica = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .build();

        Colaborador colaborador = Colaborador.builder()
                .id(1L)
                .nombre("Ana")
                .apellido("Gomez")
                .build();

        DetalleHistoriaClinica detalle = DetalleHistoriaClinica.builder()
                .id(1L)
                .temperatura(new BigDecimal("38.50"))
                .peso(new BigDecimal("12.80"))
                .frecuenciaCardiaca(new BigDecimal("95.00"))
                .frecuenciaRespiratoria(new BigDecimal("24.00"))
                .fechaHora(LocalDateTime.of(2026, 4, 19, 10, 30))
                .alimentacion("Buena")
                .hidratacion("Adecuada")
                .observacion("Paciente estable")
                .historiaClinica(historiaClinica)
                .colaborador(colaborador)
                .build();

        when(detalleHistoriaClinicaRepository.findById(1L)).thenReturn(Optional.of(detalle));

        DetalleHistoriaClinica result = detalleHistoriaClinicaService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(new BigDecimal("38.50"), result.getTemperatura());
        verify(detalleHistoriaClinicaRepository).findById(1L);
    }

    @Test
    void shouldThrowWhenDetalleHistoriaClinicaNotFoundById() {
        when(detalleHistoriaClinicaRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> detalleHistoriaClinicaService.findById(99L)
        );

        assertEquals("Detalle de historia clínica no encontrado con id: 99", exception.getMessage());
        verify(detalleHistoriaClinicaRepository).findById(99L);
    }

    @Test
    void shouldFindDetallesByHistoriaClinicaId() {
        HistoriaClinica historiaClinica = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .build();

        Colaborador colaborador = Colaborador.builder()
                .id(1L)
                .nombre("Ana")
                .apellido("Gomez")
                .build();

        DetalleHistoriaClinica detalle = DetalleHistoriaClinica.builder()
                .id(1L)
                .temperatura(new BigDecimal("38.50"))
                .peso(new BigDecimal("12.80"))
                .frecuenciaCardiaca(new BigDecimal("95.00"))
                .frecuenciaRespiratoria(new BigDecimal("24.00"))
                .fechaHora(LocalDateTime.of(2026, 4, 19, 10, 30))
                .alimentacion("Buena")
                .hidratacion("Adecuada")
                .observacion("Paciente estable")
                .historiaClinica(historiaClinica)
                .colaborador(colaborador)
                .build();

        when(detalleHistoriaClinicaRepository.findByHistoriaClinicaId(1L)).thenReturn(List.of(detalle));

        List<DetalleHistoriaClinica> result = detalleHistoriaClinicaService.findByHistoriaClinicaId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(detalleHistoriaClinicaRepository).findByHistoriaClinicaId(1L);
    }

    @Test
    void shouldCreateDetalleHistoriaClinica() {
        HistoriaClinica historiaClinica = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .build();

        Colaborador colaborador = Colaborador.builder()
                .id(1L)
                .nombre("Ana")
                .apellido("Gomez")
                .build();

        DetalleHistoriaClinicaRequest request = new DetalleHistoriaClinicaRequest();
        request.setTemperatura(new BigDecimal("38.50"));
        request.setPeso(new BigDecimal("12.80"));
        request.setFrecuenciaCardiaca(new BigDecimal("95.00"));
        request.setFrecuenciaRespiratoria(new BigDecimal("24.00"));
        request.setFechaHora(LocalDateTime.of(2026, 4, 19, 10, 30));
        request.setAlimentacion("Buena");
        request.setHidratacion("Adecuada");
        request.setObservacion("Paciente estable");
        request.setHistoriaClinicaId(1L);
        request.setColaboradorId(1L);

        DetalleHistoriaClinica detalleGuardado = DetalleHistoriaClinica.builder()
                .id(1L)
                .temperatura(new BigDecimal("38.50"))
                .peso(new BigDecimal("12.80"))
                .frecuenciaCardiaca(new BigDecimal("95.00"))
                .frecuenciaRespiratoria(new BigDecimal("24.00"))
                .fechaHora(LocalDateTime.of(2026, 4, 19, 10, 30))
                .alimentacion("Buena")
                .hidratacion("Adecuada")
                .observacion("Paciente estable")
                .historiaClinica(historiaClinica)
                .colaborador(colaborador)
                .build();

        when(historiaClinicaRepository.findById(1L)).thenReturn(Optional.of(historiaClinica));
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(colaborador));
        when(detalleHistoriaClinicaRepository.save(any(DetalleHistoriaClinica.class))).thenReturn(detalleGuardado);

        DetalleHistoriaClinica result = detalleHistoriaClinicaService.create(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(new BigDecimal("38.50"), result.getTemperatura());
        verify(historiaClinicaRepository).findById(1L);
        verify(colaboradorRepository).findById(1L);
        verify(detalleHistoriaClinicaRepository).save(any(DetalleHistoriaClinica.class));
    }

    @Test
    void shouldThrowWhenHistoriaClinicaNotFoundCreatingDetalle() {
        DetalleHistoriaClinicaRequest request = new DetalleHistoriaClinicaRequest();
        request.setTemperatura(new BigDecimal("38.50"));
        request.setPeso(new BigDecimal("12.80"));
        request.setFrecuenciaCardiaca(new BigDecimal("95.00"));
        request.setFrecuenciaRespiratoria(new BigDecimal("24.00"));
        request.setFechaHora(LocalDateTime.of(2026, 4, 19, 10, 30));
        request.setAlimentacion("Buena");
        request.setHidratacion("Adecuada");
        request.setObservacion("Paciente estable");
        request.setHistoriaClinicaId(99L);
        request.setColaboradorId(1L);

        when(historiaClinicaRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> detalleHistoriaClinicaService.create(request)
        );

        assertEquals("Historia clínica no encontrada con id: 99", exception.getMessage());
        verify(historiaClinicaRepository).findById(99L);
        verify(colaboradorRepository, never()).findById(anyLong());
        verify(detalleHistoriaClinicaRepository, never()).save(any(DetalleHistoriaClinica.class));
    }

    @Test
    void shouldThrowWhenColaboradorNotFoundCreatingDetalle() {
        HistoriaClinica historiaClinica = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .build();

        DetalleHistoriaClinicaRequest request = new DetalleHistoriaClinicaRequest();
        request.setTemperatura(new BigDecimal("38.50"));
        request.setPeso(new BigDecimal("12.80"));
        request.setFrecuenciaCardiaca(new BigDecimal("95.00"));
        request.setFrecuenciaRespiratoria(new BigDecimal("24.00"));
        request.setFechaHora(LocalDateTime.of(2026, 4, 19, 10, 30));
        request.setAlimentacion("Buena");
        request.setHidratacion("Adecuada");
        request.setObservacion("Paciente estable");
        request.setHistoriaClinicaId(1L);
        request.setColaboradorId(99L);

        when(historiaClinicaRepository.findById(1L)).thenReturn(Optional.of(historiaClinica));
        when(colaboradorRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> detalleHistoriaClinicaService.create(request)
        );

        assertEquals("Colaborador no encontrado con id: 99", exception.getMessage());
        verify(historiaClinicaRepository).findById(1L);
        verify(colaboradorRepository).findById(99L);
        verify(detalleHistoriaClinicaRepository, never()).save(any(DetalleHistoriaClinica.class));
    }

    @Test
    void shouldUpdateDetalleHistoriaClinica() {
        HistoriaClinica historiaClinica = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .build();

        Colaborador colaborador = Colaborador.builder()
                .id(1L)
                .nombre("Ana")
                .apellido("Gomez")
                .build();

        DetalleHistoriaClinicaRequest request = new DetalleHistoriaClinicaRequest();
        request.setTemperatura(new BigDecimal("39.00"));
        request.setPeso(new BigDecimal("13.00"));
        request.setFrecuenciaCardiaca(new BigDecimal("98.00"));
        request.setFrecuenciaRespiratoria(new BigDecimal("25.00"));
        request.setFechaHora(LocalDateTime.of(2026, 4, 19, 11, 0));
        request.setAlimentacion("Regular");
        request.setHidratacion("Adecuada");
        request.setObservacion("Se recomienda observación");
        request.setHistoriaClinicaId(1L);
        request.setColaboradorId(1L);

        DetalleHistoriaClinica detalleExistente = DetalleHistoriaClinica.builder()
                .id(1L)
                .temperatura(new BigDecimal("38.50"))
                .peso(new BigDecimal("12.80"))
                .frecuenciaCardiaca(new BigDecimal("95.00"))
                .frecuenciaRespiratoria(new BigDecimal("24.00"))
                .fechaHora(LocalDateTime.of(2026, 4, 19, 10, 30))
                .alimentacion("Buena")
                .hidratacion("Adecuada")
                .observacion("Paciente estable")
                .historiaClinica(historiaClinica)
                .colaborador(colaborador)
                .build();

        DetalleHistoriaClinica detalleActualizado = DetalleHistoriaClinica.builder()
                .id(1L)
                .temperatura(new BigDecimal("39.00"))
                .peso(new BigDecimal("13.00"))
                .frecuenciaCardiaca(new BigDecimal("98.00"))
                .frecuenciaRespiratoria(new BigDecimal("25.00"))
                .fechaHora(LocalDateTime.of(2026, 4, 19, 11, 0))
                .alimentacion("Regular")
                .hidratacion("Adecuada")
                .observacion("Se recomienda observación")
                .historiaClinica(historiaClinica)
                .colaborador(colaborador)
                .build();

        when(detalleHistoriaClinicaRepository.findById(1L)).thenReturn(Optional.of(detalleExistente));
        when(historiaClinicaRepository.findById(1L)).thenReturn(Optional.of(historiaClinica));
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(colaborador));
        when(detalleHistoriaClinicaRepository.save(any(DetalleHistoriaClinica.class))).thenReturn(detalleActualizado);

        DetalleHistoriaClinica result = detalleHistoriaClinicaService.update(1L, request);

        assertNotNull(result);
        assertEquals(new BigDecimal("39.00"), result.getTemperatura());
        assertEquals("Regular", result.getAlimentacion());
        verify(detalleHistoriaClinicaRepository).findById(1L);
        verify(historiaClinicaRepository).findById(1L);
        verify(colaboradorRepository).findById(1L);
        verify(detalleHistoriaClinicaRepository).save(any(DetalleHistoriaClinica.class));
    }

    @Test
    void shouldThrowWhenUpdatingDetalleNotFound() {
        DetalleHistoriaClinicaRequest request = new DetalleHistoriaClinicaRequest();
        request.setTemperatura(new BigDecimal("39.00"));
        request.setPeso(new BigDecimal("13.00"));
        request.setFrecuenciaCardiaca(new BigDecimal("98.00"));
        request.setFrecuenciaRespiratoria(new BigDecimal("25.00"));
        request.setFechaHora(LocalDateTime.of(2026, 4, 19, 11, 0));
        request.setAlimentacion("Regular");
        request.setHidratacion("Adecuada");
        request.setObservacion("Se recomienda observación");
        request.setHistoriaClinicaId(1L);
        request.setColaboradorId(1L);

        when(detalleHistoriaClinicaRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> detalleHistoriaClinicaService.update(99L, request)
        );

        assertEquals("Detalle de historia clínica no encontrado con id: 99", exception.getMessage());
        verify(detalleHistoriaClinicaRepository).findById(99L);
        verify(detalleHistoriaClinicaRepository, never()).save(any(DetalleHistoriaClinica.class));
    }

    @Test
    void shouldThrowWhenHistoriaClinicaNotFoundUpdatingDetalle() {
        Colaborador colaborador = Colaborador.builder()
                .id(1L)
                .nombre("Ana")
                .apellido("Gomez")
                .build();

        DetalleHistoriaClinica detalleExistente = DetalleHistoriaClinica.builder()
                .id(1L)
                .temperatura(new BigDecimal("38.50"))
                .build();

        DetalleHistoriaClinicaRequest request = new DetalleHistoriaClinicaRequest();
        request.setTemperatura(new BigDecimal("39.00"));
        request.setPeso(new BigDecimal("13.00"));
        request.setFrecuenciaCardiaca(new BigDecimal("98.00"));
        request.setFrecuenciaRespiratoria(new BigDecimal("25.00"));
        request.setFechaHora(LocalDateTime.of(2026, 4, 19, 11, 0));
        request.setAlimentacion("Regular");
        request.setHidratacion("Adecuada");
        request.setObservacion("Se recomienda observación");
        request.setHistoriaClinicaId(99L);
        request.setColaboradorId(1L);

        when(detalleHistoriaClinicaRepository.findById(1L)).thenReturn(Optional.of(detalleExistente));
        when(historiaClinicaRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> detalleHistoriaClinicaService.update(1L, request)
        );

        assertEquals("Historia clínica no encontrada con id: 99", exception.getMessage());
        verify(historiaClinicaRepository).findById(99L);
        verify(colaboradorRepository, never()).findById(anyLong());
        verify(detalleHistoriaClinicaRepository, never()).save(any(DetalleHistoriaClinica.class));
    }

    @Test
    void shouldThrowWhenColaboradorNotFoundUpdatingDetalle() {
        HistoriaClinica historiaClinica = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .build();

        DetalleHistoriaClinica detalleExistente = DetalleHistoriaClinica.builder()
                .id(1L)
                .temperatura(new BigDecimal("38.50"))
                .build();

        DetalleHistoriaClinicaRequest request = new DetalleHistoriaClinicaRequest();
        request.setTemperatura(new BigDecimal("39.00"));
        request.setPeso(new BigDecimal("13.00"));
        request.setFrecuenciaCardiaca(new BigDecimal("98.00"));
        request.setFrecuenciaRespiratoria(new BigDecimal("25.00"));
        request.setFechaHora(LocalDateTime.of(2026, 4, 19, 11, 0));
        request.setAlimentacion("Regular");
        request.setHidratacion("Adecuada");
        request.setObservacion("Se recomienda observación");
        request.setHistoriaClinicaId(1L);
        request.setColaboradorId(99L);

        when(detalleHistoriaClinicaRepository.findById(1L)).thenReturn(Optional.of(detalleExistente));
        when(historiaClinicaRepository.findById(1L)).thenReturn(Optional.of(historiaClinica));
        when(colaboradorRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> detalleHistoriaClinicaService.update(1L, request)
        );

        assertEquals("Colaborador no encontrado con id: 99", exception.getMessage());
        verify(historiaClinicaRepository).findById(1L);
        verify(colaboradorRepository).findById(99L);
        verify(detalleHistoriaClinicaRepository, never()).save(any(DetalleHistoriaClinica.class));
    }

    @Test
    void shouldDeleteDetalleHistoriaClinica() {
        DetalleHistoriaClinica detalle = DetalleHistoriaClinica.builder()
                .id(1L)
                .temperatura(new BigDecimal("38.50"))
                .build();

        when(detalleHistoriaClinicaRepository.findById(1L)).thenReturn(Optional.of(detalle));

        detalleHistoriaClinicaService.delete(1L);

        verify(detalleHistoriaClinicaRepository).findById(1L);
        verify(detalleHistoriaClinicaRepository).delete(detalle);
    }
}