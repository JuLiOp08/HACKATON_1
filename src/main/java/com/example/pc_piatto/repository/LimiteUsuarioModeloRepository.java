package com.example.pc_piatto.repository;

import com.example.pc_piatto.domain.LimiteUsuarioModelo;
import com.example.pc_piatto.domain.Usuario;
import com.example.pc_piatto.domain.ModeloIA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LimiteUsuarioModeloRepository extends JpaRepository<LimiteUsuarioModelo, Long> {
}
