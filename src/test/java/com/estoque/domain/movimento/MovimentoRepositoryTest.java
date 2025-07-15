package com.estoque.domain.movimento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovimentoRepositoryTest {

    @Mock
    private MovimentoRepository movimentoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveRetornarMovimentosPorProdutoIdComSucesso() {
        // Arrange
        Long produtoId = 1L;
        Movimento movimento1 = mock(Movimento.class);
        Movimento movimento2 = mock(Movimento.class);
        when(movimentoRepository.findByProdutoId(produtoId)).thenReturn(List.of(movimento1, movimento2));

        // Act
        List<Movimento> movimentos = movimentoRepository.findByProdutoId(produtoId);

        // Assert
        assertNotNull(movimentos);
        assertEquals(2, movimentos.size());
        verify(movimentoRepository, times(1)).findByProdutoId(produtoId);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremMovimentosParaProdutoId() {
        // Arrange
        Long produtoId = 2L;
        when(movimentoRepository.findByProdutoId(produtoId)).thenReturn(List.of());

        // Act
        List<Movimento> movimentos = movimentoRepository.findByProdutoId(produtoId);

        // Assert
        assertNotNull(movimentos);
        assertTrue(movimentos.isEmpty());
        verify(movimentoRepository, times(1)).findByProdutoId(produtoId);
    }

    @Test
    void deveFalharAoBuscarMovimentosComProdutoIdNulo() {
        // Arrange
        doThrow(new IllegalArgumentException("Produto ID não pode ser nulo"))
                .when(movimentoRepository).findByProdutoId(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> movimentoRepository.findByProdutoId(null));
        verify(movimentoRepository, times(1)).findByProdutoId(null);
    }
}