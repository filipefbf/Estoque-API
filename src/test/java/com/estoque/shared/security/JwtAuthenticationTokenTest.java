package com.estoque.shared.security;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtAuthenticationTokenTest {

    @Mock
    private UserDetails userDetails;

    @Test
    void deveRetornarPrincipalCorretamente() {
        // Arrange
        userDetails = mock(UserDetails.class);
        when(userDetails.getAuthorities()).thenReturn(Collections.emptyList());
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(userDetails);

        // Act
        Object principal = jwtAuthenticationToken.getPrincipal();

        // Assert
        assertEquals(userDetails, principal, "Principal should match the provided UserDetails");
    }

    @Test
    void deveRetornarCredenciaisComoNull() {
        // Arrange
        userDetails = mock(UserDetails.class);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(userDetails);

        // Act
        Object credentials = jwtAuthenticationToken.getCredentials();

        // Assert
        assertNull(credentials, "Credentials should be null for JwtAuthenticationToken");
    }

    @Test
    void deveEstarAutenticadoPorPadrao() {
        // Arrange
        userDetails = mock(UserDetails.class);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(userDetails);

        // Act
        boolean isAuthenticated = jwtAuthenticationToken.isAuthenticated();

        // Assert
        assertTrue(isAuthenticated, "JwtAuthenticationToken should be authenticated by default");
    }
}