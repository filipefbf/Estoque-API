package com.estoque.interfaces.dto;

import com.estoque.shared.security.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestTest {

    @Test
    void deveCriarRegisterRequestComSucesso() {
        // Arrange
        String username = "admin";
        String password = "password123";
        Role role = Role.ADMIN;

        // Act
        RegisterRequest registerRequest = new RegisterRequest(username, password, role);

        // Assert
        assertEquals(username, registerRequest.username());
        assertEquals(password, registerRequest.password());
        assertEquals(role, registerRequest.role());
    }

    @Test
    void deveTestarEqualsEHashCode() {
        // Arrange
        RegisterRequest registerRequest1 = new RegisterRequest("admin", "password123", Role.ADMIN);
        RegisterRequest registerRequest2 = new RegisterRequest("admin", "password123", Role.ADMIN);

        // Act & Assert
        assertEquals(registerRequest1, registerRequest2);
        assertEquals(registerRequest1.hashCode(), registerRequest2.hashCode());
    }

    @Test
    void deveTestarToString() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest("admin", "password123", Role.ADMIN);

        // Act
        String result = registerRequest.toString();

        // Assert
        assertTrue(result.contains("username=admin"));
        assertTrue(result.contains("password=password123"));
        assertTrue(result.contains("role=ADMIN"));
    }
}