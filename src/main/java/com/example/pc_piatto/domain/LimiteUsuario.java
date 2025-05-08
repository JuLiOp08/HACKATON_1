package com.example.pc_piatto.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "limites")
public class LimiteUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int solicitudesPermitidas;
    private int tokensPermitidos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}