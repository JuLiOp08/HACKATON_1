package com.example.pc_piatto.dto;

import lombok.Data;

@Data
public class EmpresaDTO {
    private Long id;
    private String nombre;
    private String ruc;
    private String fechaAfiliacion;
    private boolean estadoActivo;
}
