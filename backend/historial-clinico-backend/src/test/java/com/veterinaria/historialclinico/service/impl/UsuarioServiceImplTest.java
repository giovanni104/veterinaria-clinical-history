package com.veterinaria.historialclinico.service.impl;

import com.veterinaria.historialclinico.dto.UsuarioRequest;
import com.veterinaria.historialclinico.entity.Usuario;
import com.veterinaria.historialclinico.exception.BusinessException;
import com.veterinaria.historialclinico.exception.ResourceNotFoundException;
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
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private MascotaRepository mascotaRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void shouldFindAllUsuarios() {
        Usuario usuario1 = Usuario.builder()
                .id(1L)
                .nombre("Carlos")
                .apellido("Ramirez")
                .correo("carlos@email.com")
                .tipoDocumento("CC")
                .documentoIdentificacion("123")
                .estado("ACTIVO")
                .sexo("Masculino")
                .build();

        Usuario usuario2 = Usuario.builder()
                .id(2L)
                .nombre("Laura")
                .apellido("Gomez")
                .correo("laura@email.com")
                .tipoDocumento("CC")
                .documentoIdentificacion("456")
                .estado("ACTIVO")
                .sexo("Femenino")
                .build();

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario1, usuario2));

        List<Usuario> result = usuarioService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void shouldFindUsuarioById() {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Carlos")
                .apellido("Ramirez")
                .correo("carlos@email.com")
                .tipoDocumento("CC")
                .documentoIdentificacion("123")
                .estado("ACTIVO")
                .sexo("Masculino")
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Carlos", result.getNombre());
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void shouldThrowWhenUsuarioNotFoundById() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> usuarioService.findById(99L)
        );

        assertEquals("Usuario no encontrado con id: 99", exception.getMessage());
        verify(usuarioRepository).findById(99L);
    }

    @Test
    void shouldCreateUsuario() {
        UsuarioRequest request = new UsuarioRequest();
        request.setNombre("Carlos");
        request.setApellido("Ramirez");
        request.setCorreo("carlos@email.com");
        request.setTipoDocumento("CC");
        request.setDocumentoIdentificacion("123");
        request.setEstado("ACTIVO");
        request.setSexo("Masculino");

        Usuario usuarioGuardado = Usuario.builder()
                .id(1L)
                .nombre("Carlos")
                .apellido("Ramirez")
                .correo("carlos@email.com")
                .tipoDocumento("CC")
                .documentoIdentificacion("123")
                .estado("ACTIVO")
                .sexo("Masculino")
                .build();

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        Usuario result = usuarioService.create(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Carlos", result.getNombre());
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void shouldUpdateUsuario() {
        UsuarioRequest request = new UsuarioRequest();
        request.setNombre("Carlos Andres");
        request.setApellido("Ramirez");
        request.setCorreo("carlos@email.com");
        request.setTipoDocumento("CC");
        request.setDocumentoIdentificacion("123");
        request.setEstado("ACTIVO");
        request.setSexo("Masculino");

        Usuario usuarioExistente = Usuario.builder()
                .id(1L)
                .nombre("Carlos")
                .apellido("Ramirez")
                .correo("carlos_old@email.com")
                .tipoDocumento("CC")
                .documentoIdentificacion("123")
                .estado("INACTIVO")
                .sexo("Masculino")
                .build();

        Usuario usuarioActualizado = Usuario.builder()
                .id(1L)
                .nombre("Carlos Andres")
                .apellido("Ramirez")
                .correo("carlos@email.com")
                .tipoDocumento("CC")
                .documentoIdentificacion("123")
                .estado("ACTIVO")
                .sexo("Masculino")
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioActualizado);

        Usuario result = usuarioService.update(1L, request);

        assertNotNull(result);
        assertEquals("Carlos Andres", result.getNombre());
        assertEquals("ACTIVO", result.getEstado());
        verify(usuarioRepository).findById(1L);
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void shouldThrowWhenUpdatingUsuarioNotFound() {
        UsuarioRequest request = new UsuarioRequest();
        request.setNombre("Carlos");
        request.setApellido("Ramirez");
        request.setCorreo("carlos@email.com");
        request.setTipoDocumento("CC");
        request.setDocumentoIdentificacion("123");
        request.setEstado("ACTIVO");
        request.setSexo("Masculino");

        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> usuarioService.update(99L, request)
        );

        assertEquals("Usuario no encontrado con id: 99", exception.getMessage());
        verify(usuarioRepository).findById(99L);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void shouldDeleteUsuarioWhenNoMascotasAssociated() {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Carlos")
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(mascotaRepository.existsByUsuarioId(1L)).thenReturn(false);

        usuarioService.delete(1L);

        verify(usuarioRepository).findById(1L);
        verify(mascotaRepository).existsByUsuarioId(1L);
        verify(usuarioRepository).delete(usuario);
    }

    @Test
    void shouldThrowBusinessExceptionWhenDeletingUsuarioWithMascotas() {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Carlos")
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(mascotaRepository.existsByUsuarioId(1L)).thenReturn(true);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> usuarioService.delete(1L)
        );

        assertEquals("No se puede eliminar el usuario porque tiene mascotas asociadas", exception.getMessage());
        verify(usuarioRepository).findById(1L);
        verify(mascotaRepository).existsByUsuarioId(1L);
        verify(usuarioRepository, never()).delete(any(Usuario.class));
    }
}