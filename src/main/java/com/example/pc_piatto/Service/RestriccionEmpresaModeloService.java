package com.example.pc_piatto.Service;


import com.example.pc_piatto.domain.Empresa;
import com.example.pc_piatto.domain.ModeloIA;
import com.example.pc_piatto.domain.RestriccionEmpresaModelo;
import com.example.pc_piatto.dto.RestriccionEmpresaModeloDTO;
import com.example.pc_piatto.repository.EmpresaRepository;
import com.example.pc_piatto.repository.ModeloIARepository;
import com.example.pc_piatto.repository.RestriccionEmpresaModeloRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestriccionEmpresaModeloService {

    @Autowired
    private RestriccionEmpresaModeloRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ModeloIARepository modeloIARepository;

    @Autowired
    private ModelMapper modelMapper;

    public RestriccionEmpresaModeloDTO crearRestriccion(RestriccionEmpresaModeloDTO dto) {
        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        ModeloIA modelo = modeloIARepository.findById(dto.getModeloId())
                .orElseThrow(() -> new RuntimeException("Modelo IA no encontrado"));

        RestriccionEmpresaModelo restriccion = new RestriccionEmpresaModelo();
        restriccion.setEmpresa(empresa);
        restriccion.setModelo(modelo);
        restriccion.setTipoLimite(dto.getTipoLimite());
        restriccion.setValorMaximo(dto.getValorMaximo());

        return modelMapper.map(repository.save(restriccion), RestriccionEmpresaModeloDTO.class);
    }

    public List<RestriccionEmpresaModeloDTO> listarRestricciones() {
        return repository.findAll().stream()
                .map(r -> modelMapper.map(r, RestriccionEmpresaModeloDTO.class))
                .collect(Collectors.toList());
    }

    public RestriccionEmpresaModeloDTO actualizarRestriccion(Long id, RestriccionEmpresaModeloDTO dto) {
        RestriccionEmpresaModelo restriccion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restricción no encontrada"));

        restriccion.setTipoLimite(dto.getTipoLimite());
        restriccion.setValorMaximo(dto.getValorMaximo());

        return modelMapper.map(repository.save(restriccion), RestriccionEmpresaModeloDTO.class);
    }

    public void eliminarRestriccion(Long id) {
        repository.deleteById(id);
    }
}