package com.estoque.domain.usuario;

import com.estoque.shared.security.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void deveSetarEObterIdComSucesso() {
        // Arrange
        Usuario usuario = new Usuario();
        Long id = 1L;

        // Act
        usuario.setId(id);

        // Assert
        assertEquals(id, usuario.getId());
    }

    @Test
    void deveSetarEObterUsernameComSucesso() {
        // Arrange
        Usuario usuario = new Usuario();
        String username = "testuser";

        // Act
        usuario.setUsername(username);

        // Assert
        assertEquals(username, usuario.getUsername());
    }

    @Test
    void deveSetarEObterPasswordComSucesso() {
        // Arrange
        Usuario usuario = new Usuario();
        String password = "password";

        // Act
        usuario.setPassword(password);

        // Assert
        assertEquals(password, usuario.getPassword());
    }

    @Test
    void deveSetarEObterRoleComSucesso() {
        // Arrange
        Usuario usuario = new Usuario();
        Role role = Role.USER;

        // Act
        usuario.setRole(role);

        // Assert
        assertEquals(role, usuario.getRole());
    }
}