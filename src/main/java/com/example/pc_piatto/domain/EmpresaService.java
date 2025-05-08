package com.example.pc_piatto.domain;

import com.example.pc_piatto.dto.EmpresaDTO;
import com.example.pc_piatto.repository.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private ModelMapper modelMapper;

    public EmpresaDTO crearEmpresa(EmpresaDTO dto) {
        Empresa empresa = modelMapper.map(dto, Empresa.class);
        Empresa guardada = empresaRepo.save(empresa);
        return modelMapper.map(guardada, EmpresaDTO.class);
    }

    public List<EmpresaDTO> listarEmpresas() {
        return empresaRepo.findAll().stream()
                .map(empresa -> modelMapper.map(empresa, EmpresaDTO.class))
                .collect(Collectors.toList());
    }

    public EmpresaDTO obtenerEmpresaPorId(Long id) {
        Empresa empresa = empresaRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada"));
        return modelMapper.map(empresa, EmpresaDTO.class);
    }

    public EmpresaDTO actualizarEmpresa(Long id, EmpresaDTO dto) {
        Empresa empresa = empresaRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada"));

        empresa.setNombre(dto.getNombre());
        empresa.setRuc(dto.getRuc());
        empresa.setFechaAfiliacion(dto.getFechaAfiliacion());
        empresa.setEstadoActivo(dto.isEstadoActivo());

        Empresa actualizada = empresaRepo.save(empresa);
        return modelMapper.map(actualizada, EmpresaDTO.class);
    }

    public void eliminarEmpresa(Long id) {
        empresaRepo.deleteById(id);
    }

    public EmpresaDTO activarDesactivarEmpresa(Long id, boolean estado) {
        Empresa empresa = empresaRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada"));

        empresa.setEstadoActivo(estado);
        Empresa actualizada = empresaRepo.save(empresa);
        return modelMapper.map(actualizada, EmpresaDTO.class);
    }

    public String obtenerConsumoEmpresa(Long id) {
        // Lógica simulada, en producción esto debe calcular créditos totales, etc.
        Empresa empresa = empresaRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada"));

        return "Consumo total de la empresa: 1000 tokens";
    }
}
