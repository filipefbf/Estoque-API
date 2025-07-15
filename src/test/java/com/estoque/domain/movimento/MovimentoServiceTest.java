package com.estoque.domain.movimento;

import com.estoque.domain.produto.Produto;
import com.estoque.domain.produto.ProdutoRepository;
import com.estoque.domain.usuario.Usuario;
import com.estoque.domain.usuario.UsuarioRepository;
import com.estoque.enums.TipoMovimento;
import com.estoque.interfaces.dto.MovimentoRequest;
import com.estoque.interfaces.dto.MovimentoResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovimentoServiceTest {

    @Mock
    private ProdutoRepository produtoRepo;
    @Mock
    private MovimentoRepository movimentoRepo;
    @Mock
    private UsuarioRepository usuarioRepo;

    private MovimentoService movimentoService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movimentoService = new MovimentoService();

        Field produtoRepoField = MovimentoService.class.getDeclaredField("produtoRepo");
        produtoRepoField.setAccessible(true);
        produtoRepoField.set(movimentoService, produtoRepo);

        Field movimentoRepoField = MovimentoService.class.getDeclaredField("movimentoRepo");
        movimentoRepoField.setAccessible(true);
        movimentoRepoField.set(movimentoService, movimentoRepo);

        Field usuarioRepoField = MovimentoService.class.getDeclaredField("usuarioRepo");
        usuarioRepoField.setAccessible(true);
        usuarioRepoField.set(movimentoService, usuarioRepo);
    }

    @Test
    void deveRegistrarMovimentoComSucesso() {
        // Arrange
        Long userId = 1L;
        MovimentoRequest req = new MovimentoRequest(1L, TipoMovimento.ENTRADA, 10);
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setQuantidade(50);
        produto.setQuantidadeMinima(10); // Initialize quantidadeMinima
        Usuario usuario = new Usuario();
        usuario.setId(userId);

        when(produtoRepo.findById(req.produtoId())).thenReturn(Optional.of(produto));
        when(usuarioRepo.findById(userId)).thenReturn(Optional.of(usuario));

        // Act
        MovimentoResponse response = movimentoService.registrar(userId, req);

        // Assert
        assertNotNull(response);
        assertEquals(req.produtoId(), response.produtoId());
        assertEquals(req.tipo(), response.tipo());
        assertEquals(req.quantidade(), response.quantidade());
        verify(produtoRepo, times(1)).save(produto);
        verify(movimentoRepo, times(1)).save(any(Movimento.class));
    }

    @Test
    void deveFalharAoRegistrarMovimentoComProdutoNaoEncontrado() {
        // Arrange
        Long userId = 1L;
        MovimentoRequest req = new MovimentoRequest(1L, TipoMovimento.ENTRADA, 10);

        when(produtoRepo.findById(req.produtoId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> movimentoService.registrar(userId, req));
        verify(produtoRepo, times(1)).findById(req.produtoId());
        verify(movimentoRepo, times(0)).save(any(Movimento.class));
    }

    @Test
    void deveFalharAoRegistrarMovimentoComUsuarioNaoEncontrado() {
        // Arrange
        Long userId = 1L;
        MovimentoRequest req = new MovimentoRequest(1L, TipoMovimento.ENTRADA, 10);
        Produto produto = new Produto();
        produto.setId(1L);

        when(produtoRepo.findById(req.produtoId())).thenReturn(Optional.of(produto));
        when(usuarioRepo.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> movimentoService.registrar(userId, req));
        verify(usuarioRepo, times(1)).findById(userId);
        verify(movimentoRepo, times(0)).save(any(Movimento.class));
    }

    @Test
    void deveListarMovimentosPorProdutoComSucesso() {
        // Arrange
        Long produtoId = 1L;
        Movimento movimento1 = mock(Movimento.class);
        Movimento movimento2 = mock(Movimento.class);
        when(movimentoRepo.findByProdutoId(produtoId)).thenReturn(List.of(movimento1, movimento2));

        // Act
        List<MovimentoResponse> responses = movimentoService.listarPorProduto(produtoId);

        // Assert
        assertNotNull(responses);
        assertEquals(2, responses.size());
        verify(movimentoRepo, times(1)).findByProdutoId(produtoId);
    }

    @Test
    void deveFalharAoListarMovimentosComProdutoIdNulo() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> movimentoService.listarPorProduto(null));
        verify(movimentoRepo, times(0)).findByProdutoId(null);
    }
}