package com.example.pc_piatto.Repositories;

import com.example.pc_piatto.domain.LimiteUsuarioModelo;
import com.example.pc_piatto.domain.Usuario;
import com.example.pc_piatto.domain.ModeloIA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LimiteUsuarioModeloRepository extends JpaRepository<LimiteUsuarioModelo, Long> {
    List<LimiteUsuarioModelo> findByUsuario(Usuario usuario);
    LimiteUsuarioModelo findByUsuarioAndModelo(Usuario usuario, ModeloIA modelo);
}
