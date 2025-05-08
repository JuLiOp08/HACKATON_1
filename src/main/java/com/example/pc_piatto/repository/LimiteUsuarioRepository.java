package com.example.pc_piatto.repository;

import com.example.pc_piatto.domain.LimiteUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimiteUsuarioRepository extends JpaRepository<LimiteUsuario, Long> {

}