package com.estoque.interfaces.dto;

import com.estoque.enums.TipoMovimento;

public record MovimentoRequest(
        Long produtoId,
        TipoMovimento tipo,
        Integer quantidade
) {
}
