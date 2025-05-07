package com.example.pc_piatto.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class LimiteUsuarioModeloDTO {
    private Long id;
    private Long usuarioId;
    private Long modeloId;
    private String ventanaTiempo;
    private double valorMaximo;

    public int getTokensConsumidos() {
        // Simulación del consumo de recursos
        // Simula entre 0 y 1000 tokens
        return (int) (Math.random() * 1000);
    }

    public double getCostoCalculado() {
        // Simulación del costo calculado
        // Supongamos un costo de 1.3 por token
        return getTokensConsumidos() * 0.02;
    }
}