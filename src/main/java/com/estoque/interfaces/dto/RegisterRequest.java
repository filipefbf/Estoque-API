package com.estoque.interfaces.dto;

import com.estoque.shared.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

public record RegisterRequest (
    String username,
    String password,
    Role role ){
}