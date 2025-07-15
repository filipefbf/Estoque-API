package com.estoque.domain.usuario;

import com.estoque.shared.security.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDetailsServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userDetailsService = new UserDetailsServiceImpl(usuarioRepository); // Inject mock repository
    }

    @Test
    void deveCarregarUsuarioPorUsernameComSucesso() {
        // Arrange
        String username = "testuser";
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword("password");
        usuario.setRole(Role.USER);
        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.of(usuario));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
        verify(usuarioRepository, times(1)).findByUsername(username);
    }

    @Test
    void deveFalharAoCarregarUsuarioPorUsernameInexistente() {
        // Arrange
        String username = "nonexistentuser";
        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
        verify(usuarioRepository, times(1)).findByUsername(username);
    }
}