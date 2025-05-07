package com.example.pc_piatto.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}