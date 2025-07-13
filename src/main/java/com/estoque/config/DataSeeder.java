package com.estoque.config;

import com.estoque.entity.Produto;
import com.estoque.entity.Usuario;
import com.estoque.repository.ProdutoRepository;
import com.estoque.repository.UsuarioRepository;
import com.estoque.security.Role;
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
    private final UsuarioRepository usuarioRepository;

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
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword("admin123"); // Em produção, use um PasswordEncoder
            admin.setRole(Role.ADMIN);
            usuarioRepository.save(admin);

            Usuario user = new Usuario();
            user.setUsername("user");
            user.setPassword("user123"); // Em produção, use um PasswordEncoder
            user.setRole(Role.USER);
            usuarioRepository.save(user);

            System.out.println("✔ Usuários de exemplo inseridos.");
        }
    }
}
