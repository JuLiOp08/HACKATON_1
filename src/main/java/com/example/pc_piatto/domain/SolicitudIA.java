package com.example.pc_piatto.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private ModeloIA modelo;

    private LocalDateTime fechaHora;
    private String consulta;
    private String respuesta;
    private int tokensConsumidos;
    private double costoCalculado;
}
