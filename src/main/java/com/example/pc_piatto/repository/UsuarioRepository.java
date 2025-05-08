package com.example.pc_piatto.repository;

import com.example.pc_piatto.domain.Usuario;
import com.example.pc_piatto.domain.Empresa;
import com.example.pc_piatto.domain.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByEmpresaId(Long empresaId);
    Optional<Usuario> findByCorreo(String correo);
}