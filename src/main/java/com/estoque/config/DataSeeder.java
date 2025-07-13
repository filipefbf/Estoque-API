package com.estoque.config;

import com.estoque.entity.Produto;
import com.estoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev") // Só roda no profile de desenvolvimento
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) {
        if (produtoRepository.count() == 0) {
            List<Produto> produtos = List.of(
                    new Produto(null, "Teclado Mecânico", 50, 10),
                    new Produto(null, "Mouse Gamer", 100, 20),
                    new Produto(null, "Monitor 24\"", 30, 5),
                    new Produto(null, "Notebook i5", 20, 5)
            );
            produtoRepository.saveAll(produtos);
            System.out.println("✔ Produtos de exemplo inseridos.");
        }
    }
}
