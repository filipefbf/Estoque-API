package com.estoque.interfaces.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthRequestTest {

    @Test
    void deveCriarAuthRequestComSucesso() {
        // Arrange
        AuthRequest authRequest = new AuthRequest("admin", "password123");

        // Act & Assert
        assertEquals("admin", authRequest.username());
        assertEquals("password123", authRequest.password());
    }

    @Test
    void deveTestarEqualsEHashCode() {
        // Arrange
        AuthRequest authRequest1 = new AuthRequest("admin", "password123");

        AuthRequest authRequest2 = new AuthRequest("admin", "password123");

        // Act & Assert
        assertEquals(authRequest1, authRequest2);
        assertEquals(authRequest1.hashCode(), authRequest2.hashCode());
    }

    @Test
    void deveTestarToString() {
        // Arrange
        AuthRequest authRequest = new AuthRequest("admin", "password123");

        // Act
        String result = authRequest.toString();

        // Assert
        assertTrue(result.contains("username=admin"));
        assertTrue(result.contains("password=password123"));
    }
}