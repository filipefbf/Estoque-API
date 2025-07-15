package com.estoque.shared.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        userDetails = mock(UserDetails.class);
    }

    @Test
    void deveGerarTokenCorretamente() {
        // Arrange
        User user = new User("testUser", "password", Collections.emptyList());

        // Act
        String token = jwtUtil.generateToken(user);

        // Assert
        assertNotNull(token, "Generated token should not be null");
    }

    @Test
    void deveExtrairUsernameCorretamente() {
        // Arrange
        User user = new User("testUser", "password", Collections.emptyList());
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = jwtUtil.generateToken(user);

        // Act
        String username = jwtUtil.extractUsername(token);

        // Assert
        assertEquals("testUser", username, "Extracted username should match the user's username");
    }

    @Test
    void deveValidarTokenCorretamente() {
        // Arrange
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = jwtUtil.generateToken(new User("testUser", "password", Collections.emptyList()));

        // Act
        boolean isValid = jwtUtil.validateToken(token, userDetails);

        // Assert
        assertTrue(isValid, "Token should be valid for the given user");
    }
}