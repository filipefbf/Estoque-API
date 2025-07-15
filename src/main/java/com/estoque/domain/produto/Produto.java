package com.estoque.domain.produto;

import com.estoque.domain.movimento.Movimento;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer quantidade;
    private Integer quantidadeMinima;
    @OneToMany(mappedBy = "produto")
    private List<Movimento> movimentos;



    public Produto(Long id, String nome, Integer quantidade, Integer quantidadeMinima) {
        if (nome == null) {
            throw new NullPointerException("Nome do produto não pode ser nulo");
        }
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
    }
}

