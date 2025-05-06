package com.example.pc_piatto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private String descripcion;

    private Integer precio;

    private Boolean enMenuDelDia = false;

    @ManyToMany
    private List<Pedido> pedidos;
}
