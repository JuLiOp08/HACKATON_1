package com.example.pc_piatto.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LimiteUsuarioModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private ModeloIA modelo;

    private String ventanaTiempo; // ejemplo: "mensual", "diaria"
    private double valorMaximo;
}
