package com.estoque.controller;

import com.estoque.entity.Produto;
import com.estoque.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @PreAuthorize("@roleValidator.hasRole('ADMIN') or @roleValidator.hasRole('USER')")
    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    @PreAuthorize("@roleValidator.hasRole('ADMIN') or @roleValidator.hasRole('USER')")
    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PreAuthorize("@roleValidator.hasRole('ADMIN')")
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @PreAuthorize("@roleValidator.hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto p) {
        return service.atualizar(id, p);
    }

    @PreAuthorize("@roleValidator.hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}