package com.example.pc_piatto.domain;

import com.example.pc_piatto.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepo;

    public Empresa crearEmpresa(Empresa empresa) {
        return empresaRepo.save(empresa);
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepo.findAll();
    }

    public Empresa obtenerEmpresaPorId(Long id) {
        return empresaRepo.findById(id).orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }
}
