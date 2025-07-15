package com.estoque.domain.usuario;

import com.estoque.shared.security.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setUsername("testuser");
        usuario.setPassword("password");
        usuario.setRole(Role.USER);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Act
        Usuario savedUsuario = usuarioRepository.save(usuario);

        // Assert
        assertNotNull(savedUsuario);
        assertEquals("testuser", savedUsuario.getUsername());
        assertEquals("password", savedUsuario.getPassword());
        assertEquals(Role.USER, savedUsuario.getRole());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void deveEncontrarUsuarioPorUsername() {
        // Arrange
        String username = "testuser";
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword("password");
        usuario.setRole(Role.USER);
        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> foundUsuario = usuarioRepository.findByUsername(username);

        // Assert
        assertTrue(foundUsuario.isPresent());
        assertEquals(username, foundUsuario.get().getUsername());
        verify(usuarioRepository, times(1)).findByUsername(username);
    }

    @Test
    void deveRetornarVazioAoBuscarUsuarioPorUsernameInexistente() {
        // Arrange
        String username = "nonexistentuser";
        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        Optional<Usuario> foundUsuario = usuarioRepository.findByUsername(username);

        // Assert
        assertFalse(foundUsuario.isPresent());
        verify(usuarioRepository, times(1)).findByUsername(username);
    }

    @Test
    void deveExcluirUsuarioComSucesso() {
        // Arrange
        Long userId = 1L;
        doNothing().when(usuarioRepository).deleteById(userId);

        // Act
        usuarioRepository.deleteById(userId);

        // Assert
        verify(usuarioRepository, times(1)).deleteById(userId);
    }
}