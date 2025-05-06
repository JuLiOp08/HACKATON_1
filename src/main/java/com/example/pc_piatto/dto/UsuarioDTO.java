package com.example.pc_piatto.dto;

import com.example.pc_piatto.domain.RolUsuario;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private RolUsuario rol;
    private Long empresaId;
}

