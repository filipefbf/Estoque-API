package com.estoque.interfaces.controller;

import com.estoque.domain.movimento.MovimentoService;
import com.estoque.enums.TipoMovimento;
import com.estoque.interfaces.dto.MovimentoRequest;
import com.estoque.interfaces.dto.MovimentoResponse;
import com.estoque.shared.security.JwtAuthenticationToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovimentoControllerTest {

    @Mock
    private MovimentoService service;

    private MovimentoController controller;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new MovimentoController(service);
    }

    @Test
    void deveRegistrarMovimentoComSucesso() {
        // Arrange
        JwtAuthenticationToken user = mock(JwtAuthenticationToken.class);
        MovimentoRequest req = new MovimentoRequest(1L, TipoMovimento.ENTRADA, 10);
        MovimentoResponse expectedResponse = new MovimentoResponse(1L, 1L, TipoMovimento.ENTRADA, 10, LocalDateTime.now());
        when(user.getName()).thenReturn("1");
        when(service.registrar(1L, req)).thenReturn(expectedResponse);

        // Act
        MovimentoResponse response = controller.registrar(user, req);

        // Assert
        assertNotNull(response);
        assertEquals(expectedResponse, response);
        verify(service, times(1)).registrar(1L, req);
    }

    @Test
    void deveListarMovimentosPorProdutoComSucesso() {
        // Arrange
        Long produtoId = 1L;
        List<MovimentoResponse> expectedResponses = List.of(
            new MovimentoResponse(1L, produtoId, TipoMovimento.ENTRADA, 10, LocalDateTime.now()),
            new MovimentoResponse(2L, produtoId, TipoMovimento.SAIDA, 5, LocalDateTime.now())
        );
        when(service.listarPorProduto(produtoId)).thenReturn(expectedResponses);

        // Act
        List<MovimentoResponse> responses = controller.listar(produtoId);

        // Assert
        assertNotNull(responses);
        assertEquals(2, responses.size());
        verify(service, times(1)).listarPorProduto(produtoId);
    }
}