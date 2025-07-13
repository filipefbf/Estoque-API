package com.estoque.dto;

import com.estoque.entity.TipoMovimento;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MovimentoResponse {
    private Long id;
    private Long produtoId;
    private TipoMovimento tipo;
    private Integer quantidade;
    private LocalDateTime data;
}
