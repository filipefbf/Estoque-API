package com.estoque.domain.movimento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
    List<Movimento> findByProdutoId(Long produtoId);
}
