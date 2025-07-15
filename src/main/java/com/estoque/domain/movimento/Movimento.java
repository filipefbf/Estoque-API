package com.estoque.domain.movimento;

import com.estoque.domain.produto.Produto;
import com.estoque.domain.usuario.Usuario;
import com.estoque.enums.TipoMovimento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Movimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoMovimento tipo; // ENTRADA | SAIDA

    private Integer quantidade;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Movimento() {
    }

    public Movimento(Long id, TipoMovimento tipo, Integer quantidade, LocalDateTime data, Produto produto, Usuario usuario) {
        if (quantidade == null) {
            throw new NullPointerException("Quantidade não pode ser nula");
        }
        if (produto == null) {
            throw new NullPointerException("Produto não pode ser nulo");
        }
        if (usuario == null) {
            throw new NullPointerException("Usuário não pode ser nulo");
        }
        this.id = id;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimento tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

