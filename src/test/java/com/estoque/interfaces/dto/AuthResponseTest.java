package com.estoque.interfaces.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseTest {

    @Test
    void deveCriarAuthResponseComSucesso() {
        // Arrange
        AuthResponse authResponse = new AuthResponse("sample-token");

        // Act & Assert
        assertEquals("sample-token", authResponse.token());
    }

    @Test
    void deveTestarEqualsEHashCode() {
        // Arrange
        AuthResponse authResponse1 = new AuthResponse("sample-token");

        AuthResponse authResponse2 = new AuthResponse("sample-token");

        // Act & Assert
        assertEquals(authResponse1, authResponse2);
        assertEquals(authResponse1.hashCode(), authResponse2.hashCode());
    }

    @Test
    void deveTestarToString() {
        // Arrange
        AuthResponse authResponse = new AuthResponse("sample-token");

        // Act
        String result = authResponse.toString();

        // Assert
        assertTrue(result.contains("token=sample-token"));
    }
}