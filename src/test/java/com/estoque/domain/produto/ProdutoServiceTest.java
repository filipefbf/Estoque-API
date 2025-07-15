package com.estoque.domain.produto;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        produtoService = new ProdutoService(produtoRepository);
    }

    @Test
    void deveListarProdutosComSucesso() {
        // Arrange
        Produto produto1 = new Produto(1L, "Produto 1", 50, 10);
        Produto produto2 = new Produto(2L, "Produto 2", 30, 5);
        when(produtoRepository.findAll()).thenReturn(List.of(produto1, produto2));

        // Act
        List<Produto> produtos = produtoService.listar();

        // Assert
        assertNotNull(produtos);
        assertEquals(2, produtos.size());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        // Arrange
        Long produtoId = 1L;
        Produto produto = new Produto(produtoId, "Produto Teste", 50, 10);
        when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(produto));

        // Act
        Produto result = produtoService.buscar(produtoId);

        // Assert
        assertNotNull(result);
        assertEquals("Produto Teste", result.getNome());
        verify(produtoRepository, times(1)).findById(produtoId);
    }

    @Test
    void deveFalharAoBuscarProdutoComIdInexistente() {
        // Arrange
        Long produtoId = 1L;
        when(produtoRepository.findById(produtoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> produtoService.buscar(produtoId));
        verify(produtoRepository, times(1)).findById(produtoId);
    }

    @Test
    void deveSalvarProdutoComSucesso() {
        // Arrange
        Produto produto = new Produto(null, "Produto Teste", 50, 10);
        Produto savedProduto = new Produto(1L, "Produto Teste", 50, 10);
        when(produtoRepository.save(produto)).thenReturn(savedProduto);

        // Act
        Produto result = produtoService.salvar(produto);

        // Assert
        assertNotNull(result.getId());
        assertEquals("Produto Teste", result.getNome());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        // Arrange
        Long produtoId = 1L;
        Produto existente = new Produto(produtoId, "Produto Teste", 50, 10);
        Produto dadosAtualizados = new Produto(null, "Produto Atualizado", 100, 20);
        Produto atualizado = new Produto(produtoId, "Produto Atualizado", 100, 20);
        when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(existente));
        when(produtoRepository.save(existente)).thenReturn(atualizado);

        // Act
        Produto result = produtoService.atualizar(produtoId, dadosAtualizados);

        // Assert
        assertNotNull(result);
        assertEquals("Produto Atualizado", result.getNome());
        assertEquals(100, result.getQuantidade());
        assertEquals(20, result.getQuantidadeMinima());
        verify(produtoRepository, times(1)).findById(produtoId);
        verify(produtoRepository, times(1)).save(existente);
    }

    @Test
    void deveExcluirProdutoComSucesso() {
        // Arrange
        Long produtoId = 1L;
        doNothing().when(produtoRepository).deleteById(produtoId);

        // Act
        produtoService.excluir(produtoId);

        // Assert
        verify(produtoRepository, times(1)).deleteById(produtoId);
    }
}