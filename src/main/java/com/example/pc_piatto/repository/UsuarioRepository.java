package com.example.pc_piatto.repository;

import com.example.pc_piatto.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombreDeUsuario(String NombreDeUsuario);

}
