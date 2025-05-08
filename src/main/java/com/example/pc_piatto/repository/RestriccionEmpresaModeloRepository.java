package com.example.pc_piatto.repository;

import com.example.pc_piatto.domain.RestriccionEmpresaModelo;
import com.example.pc_piatto.domain.Empresa;
import com.example.pc_piatto.domain.ModeloIA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestriccionEmpresaModeloRepository extends JpaRepository<RestriccionEmpresaModelo, Long> {
    List<RestriccionEmpresaModelo> findByEmpresa(Empresa empresa);
}
