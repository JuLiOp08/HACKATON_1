package com.example.pc_piatto.dto;

import lombok.Data;

@Data
public class LimiteUsuarioModeloDTO {
    private Long id;
    private Long usuarioId;
    private Long modeloId;
    private String ventanaTiempo;
    private double valorMaximo;
}