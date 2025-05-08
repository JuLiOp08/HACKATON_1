package com.example.pc_piatto.domain;

import com.example.pc_piatto.dto.ModeloIADTO;
import com.example.pc_piatto.repository.RestriccionEmpresaModeloRepository;
import com.example.pc_piatto.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModeloIAService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private RestriccionEmpresaModeloRepository restriccionRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ModeloIADTO> obtenerModelosPermitidos(Long usuarioId) {
        Usuario usuario = usuarioRepo.findById(usuarioId).orElseThrow();
        Empresa empresa = usuario.getEmpresa();

        List<RestriccionEmpresaModelo> restricciones = restriccionRepo.findByEmpresa(empresa);

        return restricciones.stream()
                .map(r -> modelMapper.map(r.getModelo(), ModeloIADTO.class))
                .collect(Collectors.toList());
    }
}