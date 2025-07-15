package com.estoque.domain.produto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void deveCriarProdutoComConstrutorCompleto() {
        // Arrange
        Long id = 1L;
        String nome = "Produto Teste";
        int quantidade = 50;
        int quantidadeMinima = 10;

        // Act
        Produto produto = new Produto(id, nome, quantidade, quantidadeMinima);

        // Assert
        assertNotNull(produto);
        assertEquals(id, produto.getId());
        assertEquals(nome, produto.getNome());
        assertEquals(quantidade, produto.getQuantidade());
        assertEquals(quantidadeMinima, produto.getQuantidadeMinima());
    }

    @Test
    void deveCriarProdutoComConstrutorSemQuantidade() {
        // Arrange
        Long id = 1L;
        String nome = "Produto Teste";

        // Act
        Produto produto = new Produto(id, nome, null, null);

        // Assert
        assertNotNull(produto);
        assertEquals(id, produto.getId());
        assertEquals(nome, produto.getNome());
        assertNull(produto.getQuantidade());
        assertNull(produto.getQuantidadeMinima());
    }

    @Test
    void deveAtualizarQuantidadeComSucesso() {
        // Arrange
        Produto produto = new Produto(1L, "Produto Teste", 50, 10);

        // Act
        produto.setQuantidade(100);

        // Assert
        assertEquals(100, produto.getQuantidade());
    }

    @Test
    void deveAtualizarQuantidadeMinimaComSucesso() {
        // Arrange
        Produto produto = new Produto(1L, "Produto Teste", 50, 10);

        // Act
        produto.setQuantidadeMinima(20);

        // Assert
        assertEquals(20, produto.getQuantidadeMinima());
    }

    @Test
    void deveFalharAoCriarProdutoComNomeNulo() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> new Produto(1L, null, 50, 10));
    }
}