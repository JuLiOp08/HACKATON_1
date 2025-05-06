package com.example.pc_piatto.dto;

import lombok.Data;

@Data
public class LimiteUsuarioModeloDto {
    private Long id;
    private Long usuarioId;
    private Long modeloId;
    private String ventanaTiempo;
    private double valorMaximo;
}