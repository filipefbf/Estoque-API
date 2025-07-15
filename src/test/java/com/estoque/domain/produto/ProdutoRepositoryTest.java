package com.estoque.domain.produto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoRepositoryTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveSalvarProdutoComSucesso() {
        // Arrange
        Produto produto = new Produto(null, "Produto Teste", 50, 10);
        Produto savedProduto = new Produto(1L, "Produto Teste", 50, 10);
        when(produtoRepository.save(produto)).thenReturn(savedProduto);

        // Act
        Produto result = produtoRepository.save(produto);

        // Assert
        assertNotNull(result.getId());
        assertEquals("Produto Teste", result.getNome());
        assertEquals(50, result.getQuantidade());
        assertEquals(10, result.getQuantidadeMinima());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void deveEncontrarProdutoPorId() {
        // Arrange
        Long produtoId = 1L;
        Produto produto = new Produto(produtoId, "Produto Teste", 50, 10);
        when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(produto));

        // Act
        Optional<Produto> result = produtoRepository.findById(produtoId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Produto Teste", result.get().getNome());
        verify(produtoRepository, times(1)).findById(produtoId);
    }

    @Test
    void deveExcluirProdutoComSucesso() {
        // Arrange
        Long produtoId = 1L;
        doNothing().when(produtoRepository).deleteById(produtoId);

        // Act
        produtoRepository.deleteById(produtoId);

        // Assert
        verify(produtoRepository, times(1)).deleteById(produtoId);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        // Arrange
        Produto produto = new Produto(1L, "Produto Teste", 50, 10);
        Produto updatedProduto = new Produto(1L, "Produto Teste", 100, 10);
        when(produtoRepository.save(produto)).thenReturn(updatedProduto);

        // Act
        Produto result = produtoRepository.save(produto);

        // Assert
        assertEquals(100, result.getQuantidade());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void deveFalharAoBuscarProdutoComIdNulo() {
        // Arrange
        Long produtoId = null;
        when(produtoRepository.findById(produtoId))
            .thenThrow(new IllegalArgumentException("Produto ID não pode ser nulo"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produtoRepository.findById(produtoId));
        verify(produtoRepository, times(1)).findById(produtoId);
    }
}