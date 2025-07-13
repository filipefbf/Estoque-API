package com.estoque.dto;

import com.estoque.entity.TipoMovimento;

public record MovimentoRequest(
        Long produtoId,
        TipoMovimento tipo,
        Integer quantidade
) {
}
