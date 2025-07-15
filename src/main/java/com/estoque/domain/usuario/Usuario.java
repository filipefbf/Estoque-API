package com.estoque.domain.usuario;

import com.estoque.domain.movimento.Movimento;
import com.estoque.shared.security.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "usuario")
    private List<Movimento> movimentos;
}
