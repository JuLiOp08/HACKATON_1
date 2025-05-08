package com.example.pc_piatto.dto;

import com.example.pc_piatto.domain.TipoLimite;
import lombok.Data;

@Data
public class RestriccionEmpresaModeloDTO {
    private Long id;
    private Long empresaId;
    private Long modeloId;
    private TipoLimite tipoLimite;
    private double valorMaximo;
}