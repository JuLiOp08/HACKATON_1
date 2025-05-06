package com.example.pc_piatto.Repositories;

import com.example.pc_piatto.domain.Usuario;
import com.example.pc_piatto.domain.Empresa;
import com.example.pc_piatto.domain.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByEmpresa(Empresa empresa);
    List<Usuario> findByEmpresaAndRol(Empresa empresa, RolUsuario rol);
}