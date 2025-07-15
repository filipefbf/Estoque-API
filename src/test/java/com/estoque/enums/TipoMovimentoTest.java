package com.estoque.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoMovimentoTest {

    @Test
    void deveConterValoresCorretos() {
        // Assert
        assertEquals("ENTRADA", TipoMovimento.ENTRADA.name());
        assertEquals("SAIDA", TipoMovimento.SAIDA.name());
    }

    @Test
    void deveRetornarEnumPorNomeComSucesso() {
        // Act & Assert
        assertEquals(TipoMovimento.ENTRADA, TipoMovimento.valueOf("ENTRADA"));
        assertEquals(TipoMovimento.SAIDA, TipoMovimento.valueOf("SAIDA"));
    }

    @Test
    void deveFalharAoRetornarEnumPorNomeInvalido() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> TipoMovimento.valueOf("INVALIDO"));
    }

    @Test
    void deveRetornarTodosOsValoresDefinidos() {
        // Act
        TipoMovimento[] valores = TipoMovimento.values();

        // Assert
        assertEquals(2, valores.length);
        assertArrayEquals(new TipoMovimento[]{TipoMovimento.ENTRADA, TipoMovimento.SAIDA}, valores);
    }
}