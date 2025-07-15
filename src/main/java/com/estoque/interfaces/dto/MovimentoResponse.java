package com.estoque.interfaces.dto;

import com.estoque.enums.TipoMovimento;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


public record MovimentoResponse (
    Long id,
    Long produtoId,
    TipoMovimento tipo,
    Integer quantidade,
    LocalDateTime data ){
}
