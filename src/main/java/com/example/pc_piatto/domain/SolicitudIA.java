package com.example.pc_piatto.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class SolicitudIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String consulta;

    @Lob
    private String respuesta;

    private int tokensConsumidos;

    private ZonedDateTime fechaHora;

    private String modeloUsado;

    private String nombreArchivo;

    private String error;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
