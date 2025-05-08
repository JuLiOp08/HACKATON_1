package com.example.pc_piatto.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolicitudIADTO {
    private Long id;
    private Long usuarioId;
    private Long modeloId;
    private LocalDateTime fechaHora;
    private String consulta;
    private String respuesta;
    private int tokensConsumidos;
    private double costoCalculado;
}