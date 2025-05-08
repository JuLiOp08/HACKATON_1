package com.example.pc_piatto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {
    private Long usuarioId;
    private int totalSolicitudes;
    private int totalTokens;
}

