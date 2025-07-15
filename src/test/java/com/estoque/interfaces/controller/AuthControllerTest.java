package com.estoque.interfaces.controller;

import com.estoque.domain.usuario.Usuario;
import com.estoque.domain.usuario.UsuarioRepository;
import com.estoque.interfaces.dto.AuthRequest;
import com.estoque.interfaces.dto.AuthResponse;
import com.estoque.interfaces.dto.RegisterRequest;
import com.estoque.shared.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;

import static com.estoque.shared.security.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UsuarioRepository repo;

    @Mock
    private org.springframework.security.crypto.password.PasswordEncoder encoder;

    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authController = new AuthController(authManager, jwtUtil, encoder, repo);
    }

    @Test
    void deveRealizarLoginComSucesso() {
        // Arrange
        AuthRequest req = new AuthRequest("testuser", "password");
        Authentication auth = mock(Authentication.class);
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(new org.springframework.security.core.userdetails.User("testuser", "password", List.of()));
        when(jwtUtil.generateToken(any(org.springframework.security.core.userdetails.User.class))).thenReturn("mockedToken");

        // Act
        AuthResponse response = authController.login(req);

        // Assert
        assertNotNull(response);
        assertEquals("mockedToken", response.token());
        verify(authManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtil, times(1)).generateToken(any(org.springframework.security.core.userdetails.User.class));
    }

    @Test
    void deveRegistrarUsuarioComSucesso() {
        // Arrange
        RegisterRequest req = new RegisterRequest("newuser", "password", USER);
        Usuario usuario = new Usuario();
        usuario.setUsername(req.username());
        usuario.setPassword("encodedPassword");
        usuario.setRole(req.role());
        when(encoder.encode(req.password())).thenReturn("encodedPassword");
        when(repo.save(any(Usuario.class))).thenReturn(usuario);
        when(jwtUtil.generateToken(any(org.springframework.security.core.userdetails.User.class))).thenReturn("mockedToken");

        // Act
        AuthResponse response = authController.register(req);

        // Assert
        assertNotNull(response);
        assertEquals("mockedToken", response.token());
        verify(repo, times(1)).save(any(Usuario.class));
        verify(jwtUtil, times(1)).generateToken(any(org.springframework.security.core.userdetails.User.class));
    }
}