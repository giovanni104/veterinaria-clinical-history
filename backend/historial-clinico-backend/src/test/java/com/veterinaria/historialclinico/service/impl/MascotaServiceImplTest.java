package com.veterinaria.historialclinico.service.impl;

import com.veterinaria.historialclinico.dto.MascotaRequest;
import com.veterinaria.historialclinico.entity.Mascota;
import com.veterinaria.historialclinico.entity.Usuario;
import com.veterinaria.historialclinico.exception.BusinessException;
import com.veterinaria.historialclinico.exception.ResourceNotFoundException;
import com.veterinaria.historialclinico.repository.HistoriaClinicaRepository;
import com.veterinaria.historialclinico.repository.MascotaRepository;
import com.veterinaria.historialclinico.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MascotaServiceImplTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private HistoriaClinicaRepository historiaClinicaRepository;

    @InjectMocks
    private MascotaServiceImpl mascotaService;

    @Test
    void shouldFindAllMascotas() {
        Usuario usuario = Usuario.builder().id(1L).nombre("Carlos").build();

        Mascota mascota1 = Mascota.builder()
                .id(1L)
                .nombre("Max")
                .raza("Labrador")
                .vacuna("Rabia")
                .sexo("Macho")
                .usuario(usuario)
                .build();

        Mascota mascota2 = Mascota.builder()
                .id(2L)
                .nombre("Luna")
                .raza("Poodle")
                .vacuna("Moquillo")
                .sexo("Hembra")
                .usuario(usuario)
                .build();

        when(mascotaRepository.findAll()).thenReturn(List.of(mascota1, mascota2));

        List<Mascota> result = mascotaService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(mascotaRepository).findAll();
    }

    @Test
    void shouldFindMascotaById() {
        Usuario usuario = Usuario.builder().id(1L).nombre("Carlos").build();

        Mascota mascota = Mascota.builder()
                .id(1L)
                .nombre("Max")
                .raza("Labrador")
                .vacuna("Rabia")
                .sexo("Macho")
                .usuario(usuario)
                .build();

        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota));

        Mascota result = mascotaService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Max", result.getNombre());
        verify(mascotaRepository).findById(1L);
    }

    @Test
    void shouldThrowWhenMascotaNotFoundById() {
        when(mascotaRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> mascotaService.findById(99L)
        );

        assertEquals("Mascota no encontrada con id: 99", exception.getMessage());
        verify(mascotaRepository).findById(99L);
    }

    @Test
    void shouldFindMascotasByUsuarioId() {
        Usuario usuario = Usuario.builder().id(1L).nombre("Carlos").build();

        Mascota mascota = Mascota.builder()
                .id(1L)
                .nombre("Max")
                .raza("Labrador")
                .vacuna("Rabia")
                .sexo("Macho")
                .usuario(usuario)
                .build();

        when(mascotaRepository.findByUsuarioId(1L)).thenReturn(List.of(mascota));

        List<Mascota> result = mascotaService.findByUsuarioId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Max", result.get(0).getNombre());
        verify(mascotaRepository).findByUsuarioId(1L);
    }

    @Test
    void shouldCreateMascota() {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Carlos")
                .build();

        MascotaRequest request = new MascotaRequest();
        request.setNombre("Max");
        request.setRaza("Labrador");
        request.setVacuna("Rabia");
        request.setSexo("Macho");
        request.setUsuarioId(1L);

        Mascota mascotaGuardada = Mascota.builder()
                .id(1L)
                .nombre("Max")
                .raza("Labrador")
                .vacuna("Rabia")
                .sexo("Macho")
                .usuario(usuario)
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(mascotaRepository.save(any(Mascota.class))).thenReturn(mascotaGuardada);

        Mascota result = mascotaService.create(request);

        assertNotNull(result);
        assertEquals("Max", result.getNombre());
        verify(usuarioRepository).findById(1L);
        verify(mascotaRepository).save(any(Mascota.class));
    }

    @Test
    void shouldThrowWhenUsuarioNotFoundCreatingMascota() {
        MascotaRequest request = new MascotaRequest();
        request.setNombre("Max");
        request.setRaza("Labrador");
        request.setVacuna("Rabia");
        request.setSexo("Macho");
        request.setUsuarioId(99L);

        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> mascotaService.create(request)
        );

        assertEquals("Usuario no encontrado con id: 99", exception.getMessage());
        verify(usuarioRepository).findById(99L);
        verify(mascotaRepository, never()).save(any(Mascota.class));
    }

    @Test
    void shouldUpdateMascota() {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Carlos")
                .build();

        MascotaRequest request = new MascotaRequest();
        request.setNombre("Max Junior");
        request.setRaza("Labrador");
        request.setVacuna("Rabia");
        request.setSexo("Macho");
        request.setUsuarioId(1L);

        Mascota mascotaExistente = Mascota.builder()
                .id(1L)
                .nombre("Max")
                .raza("Labrador")
                .vacuna("Rabia")
                .sexo("Macho")
                .usuario(usuario)
                .build();

        Mascota mascotaActualizada = Mascota.builder()
                .id(1L)
                .nombre("Max Junior")
                .raza("Labrador")
                .vacuna("Rabia")
                .sexo("Macho")
                .usuario(usuario)
                .build();

        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascotaExistente));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(mascotaRepository.save(any(Mascota.class))).thenReturn(mascotaActualizada);

        Mascota result = mascotaService.update(1L, request);

        assertNotNull(result);
        assertEquals("Max Junior", result.getNombre());
        verify(mascotaRepository).findById(1L);
        verify(usuarioRepository).findById(1L);
        verify(mascotaRepository).save(any(Mascota.class));
    }

    @Test
    void shouldThrowWhenUpdatingMascotaNotFound() {
        MascotaRequest request = new MascotaRequest();
        request.setNombre("Max");
        request.setRaza("Labrador");
        request.setVacuna("Rabia");
        request.setSexo("Macho");
        request.setUsuarioId(1L);

        when(mascotaRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> mascotaService.update(99L, request)
        );

        assertEquals("Mascota no encontrada con id: 99", exception.getMessage());
        verify(mascotaRepository).findById(99L);
        verify(mascotaRepository, never()).save(any(Mascota.class));
    }

    @Test
    void shouldThrowWhenUsuarioNotFoundUpdatingMascota() {
        MascotaRequest request = new MascotaRequest();
        request.setNombre("Max");
        request.setRaza("Labrador");
        request.setVacuna("Rabia");
        request.setSexo("Macho");
        request.setUsuarioId(99L);

        Mascota mascotaExistente = Mascota.builder()
                .id(1L)
                .nombre("Max")
                .build();

        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascotaExistente));
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> mascotaService.update(1L, request)
        );

        assertEquals("Usuario no encontrado con id: 99", exception.getMessage());
        verify(usuarioRepository).findById(99L);
        verify(mascotaRepository, never()).save(any(Mascota.class));
    }

    @Test
    void shouldDeleteMascotaWhenNoHistoriasClinicas() {
        Mascota mascota = Mascota.builder()
                .id(1L)
                .nombre("Max")
                .build();

        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota));
        when(historiaClinicaRepository.existsByMascotaId(1L)).thenReturn(false);

        mascotaService.delete(1L);

        verify(mascotaRepository).findById(1L);
        verify(historiaClinicaRepository).existsByMascotaId(1L);
        verify(mascotaRepository).delete(mascota);
    }

    @Test
    void shouldThrowBusinessExceptionWhenDeletingMascotaWithHistorias() {
        Mascota mascota = Mascota.builder()
                .id(1L)
                .nombre("Max")
                .build();

        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(mascota));
        when(historiaClinicaRepository.existsByMascotaId(1L)).thenReturn(true);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> mascotaService.delete(1L)
        );

        assertEquals("No se puede eliminar la mascota porque tiene historias clínicas asociadas", exception.getMessage());
        verify(mascotaRepository).findById(1L);
        verify(historiaClinicaRepository).existsByMascotaId(1L);
        verify(mascotaRepository, never()).delete(any(Mascota.class));
    }
}