package com.example.pc_piatto.domain;

import com.example.pc_piatto.Enum.UsuarioRol;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private UsuarioRol rol;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    private boolean activo;
}