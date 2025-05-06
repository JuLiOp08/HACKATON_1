package com.example.pc_piatto.repository;

import com.example.pc_piatto.domain.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Long> {
    List<Plato> findAllByEnMenuDelDia(Boolean enMenuDelDia);

   List<Plato> findByNombreIn(List<String> nombres);
}
