package com.estoque.shared.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void deveConterValoresCorretos() {
        // Assert
        assertEquals("ADMIN", Role.ADMIN.name(), "Role.ADMIN should have the name 'ADMIN'");
        assertEquals("USER", Role.USER.name(), "Role.USER should have the name 'USER'");
    }

    @Test
    void deveRetornarEnumCorretamente() {
        // Act & Assert
        assertEquals(Role.ADMIN, Role.valueOf("ADMIN"), "Role.valueOf should return Role.ADMIN for 'ADMIN'");
        assertEquals(Role.USER, Role.valueOf("USER"), "Role.valueOf should return Role.USER for 'USER'");
    }

    @Test
    void deveRetornarTodosOsValores() {
        // Act
        Role[] roles = Role.values();

        // Assert
        assertArrayEquals(new Role[]{Role.ADMIN, Role.USER}, roles, "Role.values should return all enum values");
    }
}