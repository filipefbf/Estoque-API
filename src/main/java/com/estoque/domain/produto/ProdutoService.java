package com.estoque.domain.produto;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repo;

    public List<Produto> listar() {
        return repo.findAll();
    }

    public Produto buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    @Transactional
    public Produto salvar(Produto p) {
        p.setId(null);
        return repo.save(p);
    }

    @Transactional
    public Produto atualizar(Long id, Produto dados) {
        Produto existente = buscar(id);
        existente.setNome(dados.getNome());
        existente.setQuantidade(dados.getQuantidade());
        existente.setQuantidadeMinima(dados.getQuantidadeMinima());
        return repo.save(existente);
    }

    @Transactional
    public void excluir(Long id) {
        repo.deleteById(id);
    }
}
