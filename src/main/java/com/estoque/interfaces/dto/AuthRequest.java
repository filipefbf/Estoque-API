package com.estoque.interfaces.dto;

public record AuthRequest (
    String username,
    String password) {
}
