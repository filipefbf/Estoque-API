package com.estoque.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Produto {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private Integer quantidade;
    private Integer quantidadeMinima;
    @Version
    private Long versao; // controle de concorrência

    public Produto(Long id, String nome, int quantidade, int quantidadeMinima) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
    }
}

