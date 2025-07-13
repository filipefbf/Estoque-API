package com.estoque.controller;

import com.estoque.dto.MovimentoRequest;
import com.estoque.dto.MovimentoResponse;
import com.estoque.security.JwtAuthenticationToken;
import com.estoque.service.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentos")
public class MovimentoController {

    @Autowired
    private MovimentoService service;

    @PostMapping
    public MovimentoResponse registrar(@AuthenticationPrincipal UserDetails user,
                                       @RequestBody MovimentoRequest req) {
        // supondo que Usuario.username seja único e armazenado em token
        Long userId = Long.parseLong(((JwtAuthenticationToken) user).getName()); // adaptar conforme UserDetails
        return service.registrar(userId, req);
    }

    @GetMapping("/produto/{id}")
    public List<MovimentoResponse> listar(@PathVariable Long id) {
        return service.listarPorProduto(id);
    }
}
