package com.estoque.shared.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

class RoleValidatorTest {

    private RoleValidator roleValidator;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        roleValidator = new RoleValidator();
    }

    @Test
    void deveRetornarFalseSeAuthenticationForNull() {
        // Arrange
        SecurityContextHolder.getContext().setAuthentication(null);

        // Act
        boolean hasRole = roleValidator.hasRole("ADMIN");

        // Assert
        assertFalse(hasRole, "Should return false if authentication is null");
    }
}