package com.estoque.domain.movimento;

import com.estoque.domain.produto.Produto;
import com.estoque.domain.produto.ProdutoRepository;
import com.estoque.domain.usuario.UsuarioRepository;
import com.estoque.enums.TipoMovimento;
import com.estoque.interfaces.dto.MovimentoRequest;
import com.estoque.interfaces.dto.MovimentoResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentoService {

    @Autowired
    private ProdutoRepository produtoRepo;
    @Autowired
    private MovimentoRepository movimentoRepo;
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Transactional
    public MovimentoResponse registrar(Long userId, MovimentoRequest req) {
        Produto produto = produtoRepo.findById(req.produtoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        if (req.tipo() == TipoMovimento.SAIDA) {
            if (produto.getQuantidade() < req.quantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente");
            }
            produto.setQuantidade(produto.getQuantidade() - req.quantidade());
        } else {
            produto.setQuantidade(produto.getQuantidade() + req.quantidade());
        }

        // alerta caso abaixo mínimo
        if (produto.getQuantidade() < produto.getQuantidadeMinima()) {
            // TODO: implementar notificação / log
        }

        Movimento mov = new Movimento();
        mov.setProduto(produto);
        mov.setQuantidade(req.quantidade());
        mov.setTipo(req.tipo());
        mov.setData(LocalDateTime.now());
        mov.setUsuario(usuarioRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado")));

        movimentoRepo.save(mov);
        produtoRepo.save(produto);

        return new MovimentoResponse(mov.getId(), produto.getId(), mov.getTipo(), mov.getQuantidade(), mov.getData());
    }

    public List<MovimentoResponse> listarPorProduto(Long produtoId) {
        return movimentoRepo.findByProdutoId(produtoId).stream()
                .map(m -> new MovimentoResponse(m.getId(), produtoId, m.getTipo(), m.getQuantidade(), m.getData()))
                .toList();
    }
}
