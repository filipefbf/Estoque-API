package com.estoque.interfaces.controller;

import com.estoque.domain.movimento.MovimentoService;
import com.estoque.interfaces.dto.MovimentoRequest;
import com.estoque.interfaces.dto.MovimentoResponse;
import com.estoque.shared.security.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentos")
public class MovimentoController {

    private final MovimentoService service;

    public MovimentoController(MovimentoService service) {
        this.service = service;
    }

    @PostMapping
    public MovimentoResponse registrar(JwtAuthenticationToken user, MovimentoRequest request) {
        Long userId = Long.parseLong(user.getName());
        return service.registrar(userId, request);
    }

    @GetMapping("/produto/{id}")
    public List<MovimentoResponse> listar(@PathVariable Long id) {
        return service.listarPorProduto(id);
    }
}
