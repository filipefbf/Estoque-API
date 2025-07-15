package com.estoque.interfaces.dto;

import com.estoque.enums.TipoMovimento;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MovimentoResponseTest {

    @Test
    void deveCriarMovimentoResponseComSucesso() {
        // Arrange
        Long id = 1L;
        Long produtoId = 2L;
        TipoMovimento tipo = TipoMovimento.ENTRADA;
        Integer quantidade = 10;
        LocalDateTime data = LocalDateTime.now();

        // Act
        MovimentoResponse movimentoResponse = new MovimentoResponse(id, produtoId, tipo, quantidade, data);

        // Assert
        assertEquals(id, movimentoResponse.id());
        assertEquals(produtoId, movimentoResponse.produtoId());
        assertEquals(tipo, movimentoResponse.tipo());
        assertEquals(quantidade, movimentoResponse.quantidade());
        assertEquals(data, movimentoResponse.data());
    }

    @Test
    void deveTestarEqualsEHashCode() {
        // Arrange
        LocalDateTime data = LocalDateTime.now();
        MovimentoResponse movimentoResponse1 = new MovimentoResponse(1L, 2L, TipoMovimento.ENTRADA, 10, data);
        MovimentoResponse movimentoResponse2 = new MovimentoResponse(1L, 2L, TipoMovimento.ENTRADA, 10, data);

        // Act & Assert
        assertEquals(movimentoResponse1, movimentoResponse2);
        assertEquals(movimentoResponse1.hashCode(), movimentoResponse2.hashCode());
    }

    @Test
    void deveTestarToString() {
        // Arrange
        LocalDateTime data = LocalDateTime.now();
        MovimentoResponse movimentoResponse = new MovimentoResponse(1L, 2L, TipoMovimento.ENTRADA, 10, data);

        // Act
        String result = movimentoResponse.toString();

        // Assert
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("produtoId=2"));
        assertTrue(result.contains("tipo=ENTRADA"));
        assertTrue(result.contains("quantidade=10"));
        assertTrue(result.contains("data=" + data.toString()));
    }
}