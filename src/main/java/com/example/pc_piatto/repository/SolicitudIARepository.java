package com.example.pc_piatto.repository;

import com.example.pc_piatto.domain.SolicitudIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudIARepository extends JpaRepository<SolicitudIA, Long> {
    List<SolicitudIA> findByUsuarioId(Long usuarioId);
    long countByUsuarioId(Long usuarioId);
    @Query("SELECT COALESCE(SUM(s.tokensConsumidos), 0) FROM SolicitudIA s WHERE s.usuario.id = :usuarioId")
    int sumTokensByUsuarioId(@Param("usuarioId") Long usuarioId);
}