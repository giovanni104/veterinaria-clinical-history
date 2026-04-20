package com.veterinaria.historialclinico.service.impl;

import com.veterinaria.historialclinico.dto.HistoriaClinicaRequest;
import com.veterinaria.historialclinico.entity.HistoriaClinica;
import com.veterinaria.historialclinico.entity.Mascota;
import com.veterinaria.historialclinico.exception.BusinessException;
import com.veterinaria.historialclinico.exception.ResourceNotFoundException;
import com.veterinaria.historialclinico.repository.DetalleHistoriaClinicaRepository;
import com.veterinaria.historialclinico.repository.HistoriaClinicaRepository;
import com.veterinaria.historialclinico.repository.MascotaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoriaClinicaServiceImplTest {

    @Mock
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private DetalleHistoriaClinicaRepository detalleHistoriaClinicaRepository;

    @InjectMocks
    private HistoriaClinicaServiceImpl historiaClinicaService;

    @Test
    void shouldFindAllHistoriasClinicas() {
        Mascota mascota = Mascota.builder().id(1L).nombre("Max").build();

        HistoriaClinica historia1 = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .mascota(mascota)
                .build();

        HistoriaClinica historia2 = HistoriaClinica.builder()
                .id(2L)
                .fechaCreacion(LocalDate.of(2026, 4, 20))
                .mascota(mascota)
                .build();

        when(historiaClinicaRepository.findAll()).thenReturn(List.of(historia1, historia2));

        List<HistoriaClinica> result = historiaClinicaService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(historiaClinicaRepository).findAll();
    }

    @Test
    void shouldFindHistoriaClinicaById() {
        Mascota mascota = Mascota.builder().id(1L).nombre("Max").build();

        HistoriaClinica historia = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .mascota(mascota)
                .build();

        when(historiaClinicaRepository.findById(1L)).thenReturn(Optional.of(historia));

        HistoriaClinica result = historiaClinicaService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(historiaClinicaRepository).findById(1L);
    }

    @Test
    void shouldThrowWhenHistoriaClinicaNotFoundById() {
        when(historiaClinicaRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> historiaClinicaService.findById(99L)
        );

        assertEquals("Historia clínica no encontrada con id: 99", exception.getMessage());
        verify(historiaClinicaRepository).findById(99L);
    }

    @Test
    void shouldFindHistoriasClinicasByMascotaId() {
        Mascota mascota = Mascota.builder().id(1L).nombre("Max").build();

        HistoriaClinica historia = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .mascota(mascota)
                .build();

        when(historiaClinicaRepository.findByMascotaId(1L)).thenReturn(List.of(historia));

        List<HistoriaClinica> result = historiaClinicaService.findByMascotaId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(historiaClinicaRepository).findByMascotaId(1L);
    }

    @Test
    void shouldCreateHistoriaClinica() {
        Mascota mascota = Mascota.builder()
                .id(1L)
                .nombre("Max")
                .build();

        HistoriaClinicaRequest request = new HistoriaClinicaRequest();
        request.setFechaCreacion(LocalDate.of(2026, 4, 19));
        request.setMascotaId(1L);

        HistoriaClinica historiaGuardada = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .mascota(mascota)
                .build();

        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota));
        when(historiaClinicaRepository.save(any(HistoriaClinica.class))).thenReturn(historiaGuardada);

        HistoriaClinica result = historiaClinicaService.create(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(mascotaRepository).findById(1L);
        verify(historiaClinicaRepository).save(any(HistoriaClinica.class));
    }

    @Test
    void shouldThrowWhenMascotaNotFoundCreatingHistoriaClinica() {
        HistoriaClinicaRequest request = new HistoriaClinicaRequest();
        request.setFechaCreacion(LocalDate.of(2026, 4, 19));
        request.setMascotaId(99L);

        when(mascotaRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> historiaClinicaService.create(request)
        );

        assertEquals("Mascota no encontrada con id: 99", exception.getMessage());
        verify(mascotaRepository).findById(99L);
        verify(historiaClinicaRepository, never()).save(any(HistoriaClinica.class));
    }

    @Test
    void shouldUpdateHistoriaClinica() {
        Mascota mascota = Mascota.builder()
                .id(1L)
                .nombre("Max")
                .build();

        HistoriaClinicaRequest request = new HistoriaClinicaRequest();
        request.setFechaCreacion(LocalDate.of(2026, 4, 20));
        request.setMascotaId(1L);

        HistoriaClinica historiaExistente = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .mascota(mascota)
                .build();

        HistoriaClinica historiaActualizada = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 20))
                .mascota(mascota)
                .build();

        when(historiaClinicaRepository.findById(1L)).thenReturn(Optional.of(historiaExistente));
        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota));
        when(historiaClinicaRepository.save(any(HistoriaClinica.class))).thenReturn(historiaActualizada);

        HistoriaClinica result = historiaClinicaService.update(1L, request);

        assertNotNull(result);
        assertEquals(LocalDate.of(2026, 4, 20), result.getFechaCreacion());
        verify(historiaClinicaRepository).findById(1L);
        verify(mascotaRepository).findById(1L);
        verify(historiaClinicaRepository).save(any(HistoriaClinica.class));
    }

    @Test
    void shouldThrowWhenUpdatingHistoriaClinicaNotFound() {
        HistoriaClinicaRequest request = new HistoriaClinicaRequest();
        request.setFechaCreacion(LocalDate.of(2026, 4, 19));
        request.setMascotaId(1L);

        when(historiaClinicaRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> historiaClinicaService.update(99L, request)
        );

        assertEquals("Historia clínica no encontrada con id: 99", exception.getMessage());
        verify(historiaClinicaRepository).findById(99L);
        verify(historiaClinicaRepository, never()).save(any(HistoriaClinica.class));
    }

    @Test
    void shouldThrowWhenMascotaNotFoundUpdatingHistoriaClinica() {
        HistoriaClinicaRequest request = new HistoriaClinicaRequest();
        request.setFechaCreacion(LocalDate.of(2026, 4, 20));
        request.setMascotaId(99L);

        HistoriaClinica historiaExistente = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .build();

        when(historiaClinicaRepository.findById(1L)).thenReturn(Optional.of(historiaExistente));
        when(mascotaRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> historiaClinicaService.update(1L, request)
        );

        assertEquals("Mascota no encontrada con id: 99", exception.getMessage());
        verify(mascotaRepository).findById(99L);
        verify(historiaClinicaRepository, never()).save(any(HistoriaClinica.class));
    }

    @Test
    void shouldDeleteHistoriaClinicaWhenNoDetallesAssociated() {
        HistoriaClinica historia = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .build();

        when(historiaClinicaRepository.findById(1L)).thenReturn(Optional.of(historia));
        when(detalleHistoriaClinicaRepository.existsByHistoriaClinicaId(1L)).thenReturn(false);

        historiaClinicaService.delete(1L);

        verify(historiaClinicaRepository).findById(1L);
        verify(detalleHistoriaClinicaRepository).existsByHistoriaClinicaId(1L);
        verify(historiaClinicaRepository).delete(historia);
    }

    @Test
    void shouldThrowBusinessExceptionWhenDeletingHistoriaClinicaWithDetalles() {
        HistoriaClinica historia = HistoriaClinica.builder()
                .id(1L)
                .fechaCreacion(LocalDate.of(2026, 4, 19))
                .build();

        when(historiaClinicaRepository.findById(1L)).thenReturn(Optional.of(historia));
        when(detalleHistoriaClinicaRepository.existsByHistoriaClinicaId(1L)).thenReturn(true);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> historiaClinicaService.delete(1L)
        );

        assertEquals("No se puede eliminar la historia clínica porque tiene detalles asociados", exception.getMessage());
        verify(historiaClinicaRepository).findById(1L);
        verify(detalleHistoriaClinicaRepository).existsByHistoriaClinicaId(1L);
        verify(historiaClinicaRepository, never()).delete(any(HistoriaClinica.class));
    }
}