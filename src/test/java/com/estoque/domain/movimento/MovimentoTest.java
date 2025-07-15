package com.estoque.domain.movimento;

import com.estoque.domain.produto.Produto;
import com.estoque.domain.usuario.Usuario;
import com.estoque.enums.TipoMovimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovimentoTest {

    private Produto produtoMock;
    private Usuario usuarioMock;

    @BeforeEach
    void setUp() {
        produtoMock = mock(Produto.class);
        usuarioMock = mock(Usuario.class);
    }

    @Test
    void deveCriarMovimentoComSucesso() {
        // Arrange
        Long id = 1L;
        TipoMovimento tipo = TipoMovimento.ENTRADA;
        Integer quantidade = 10;
        LocalDateTime data = LocalDateTime.now();

        // Act
        Movimento movimento = new Movimento(id, tipo, quantidade, data, produtoMock, usuarioMock);

        // Assert
        assertNotNull(movimento);
        assertEquals(id, movimento.getId());
        assertEquals(tipo, movimento.getTipo());
        assertEquals(quantidade, movimento.getQuantidade());
        assertEquals(data, movimento.getData());
        assertEquals(produtoMock, movimento.getProduto());
        assertEquals(usuarioMock, movimento.getUsuario());
    }

    @Test
    void deveFalharAoCriarMovimentoComQuantidadeNula() {
        // Arrange
        Long id = 1L;
        TipoMovimento tipo = TipoMovimento.ENTRADA;
        LocalDateTime data = LocalDateTime.now();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            new Movimento(id, tipo, null, data, produtoMock, usuarioMock);
        });
    }

    @Test
    void deveFalharAoCriarMovimentoComProdutoNulo() {
        // Arrange
        Long id = 1L;
        TipoMovimento tipo = TipoMovimento.ENTRADA;
        Integer quantidade = 10;
        LocalDateTime data = LocalDateTime.now();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            new Movimento(id, tipo, quantidade, data, null, usuarioMock);
        });
    }

    @Test
    void deveFalharAoCriarMovimentoComUsuarioNulo() {
        // Arrange
        Long id = 1L;
        TipoMovimento tipo = TipoMovimento.ENTRADA;
        Integer quantidade = 10;
        LocalDateTime data = LocalDateTime.now();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            new Movimento(id, tipo, quantidade, data, produtoMock, null);
        });
    }
}