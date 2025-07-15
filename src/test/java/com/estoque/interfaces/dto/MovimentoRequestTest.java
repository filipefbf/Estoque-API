package com.estoque.interfaces.dto;

import com.estoque.enums.TipoMovimento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovimentoRequestTest {

    @Test
    void deveCriarMovimentoRequestComSucesso() {
        // Arrange
        Long produtoId = 1L;
        TipoMovimento tipo = TipoMovimento.ENTRADA;
        Integer quantidade = 10;

        // Act
        MovimentoRequest movimentoRequest = new MovimentoRequest(produtoId, tipo, quantidade);

        // Assert
        assertEquals(produtoId, movimentoRequest.produtoId());
        assertEquals(tipo, movimentoRequest.tipo());
        assertEquals(quantidade, movimentoRequest.quantidade());
    }

    @Test
    void deveTestarEqualsEHashCode() {
        // Arrange
        MovimentoRequest movimentoRequest1 = new MovimentoRequest(1L, TipoMovimento.ENTRADA, 10);
        MovimentoRequest movimentoRequest2 = new MovimentoRequest(1L, TipoMovimento.ENTRADA, 10);

        // Act & Assert
        assertEquals(movimentoRequest1, movimentoRequest2);
        assertEquals(movimentoRequest1.hashCode(), movimentoRequest2.hashCode());
    }

    @Test
    void deveTestarToString() {
        // Arrange
        MovimentoRequest movimentoRequest = new MovimentoRequest(1L, TipoMovimento.ENTRADA, 10);

        // Act
        String result = movimentoRequest.toString();

        // Assert
        assertTrue(result.contains("produtoId=1"));
        assertTrue(result.contains("tipo=ENTRADA"));
        assertTrue(result.contains("quantidade=10"));
    }
}