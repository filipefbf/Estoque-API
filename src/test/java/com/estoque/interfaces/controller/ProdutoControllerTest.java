package com.estoque.interfaces.controller;

import com.estoque.domain.produto.Produto;
import com.estoque.domain.produto.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    @Mock
    private ProdutoService service;

    private ProdutoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new ProdutoController(service);
    }

    @Test
    void deveListarProdutosComSucesso() {
        // Arrange
        List<Produto> produtos = List.of(
            new Produto(1L, "Produto 1", 10, 100),
            new Produto(2L, "Produto 2", 5, 50)
        );
        when(service.listar()).thenReturn(produtos);

        // Act
        List<Produto> result = controller.listar();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(service, times(1)).listar();
    }

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        // Arrange
        Produto produto = new Produto(1L, "Produto 1", 10, 100);
        when(service.buscar(1L)).thenReturn(produto);

        // Act
        Produto result = controller.buscar(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Produto 1", result.getNome());
        verify(service, times(1)).buscar(1L);
    }

    @Test
    void deveSalvarProdutoComSucesso() {
        // Arrange
        Produto produto = new Produto(null, "Novo Produto", 20, 200);
        Produto produtoSalvo = new Produto(1L, "Novo Produto", 20, 200);
        when(service.salvar(produto)).thenReturn(produtoSalvo);

        // Act
        Produto result = controller.salvar(produto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(service, times(1)).salvar(produto);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        // Arrange
        Produto produtoAtualizado = new Produto(1L, "Produto Atualizado", 15, 150);
        when(service.atualizar(1L, produtoAtualizado)).thenReturn(produtoAtualizado);

        // Act
        Produto result = controller.atualizar(1L, produtoAtualizado);

        // Assert
        assertNotNull(result);
        assertEquals("Produto Atualizado", result.getNome());
        verify(service, times(1)).atualizar(1L, produtoAtualizado);
    }

    @Test
    void deveExcluirProdutoComSucesso() {
        // Arrange
        doNothing().when(service).excluir(1L);

        // Act
        controller.excluir(1L);

        // Assert
        verify(service, times(1)).excluir(1L);
    }
}