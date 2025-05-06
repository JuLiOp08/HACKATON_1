package com.example.pc_piatto.domain;

import com.example.pc_piatto.dto.UsuarioDTO;
import com.example.pc_piatto.repository.EmpresaRepository;
import com.example.pc_piatto.repository.SolicitudIARepository;
import com.example.pc_piatto.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SolicitudIARepository solicitudIARepository;

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public UsuarioDTO obtenerUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setEmpresa(empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada")));
        Usuario guardado = usuarioRepository.save(usuario);
        return modelMapper.map(guardado, UsuarioDTO.class);
    }

    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());
        Usuario actualizado = usuarioRepository.save(usuario);
        return modelMapper.map(actualizado, UsuarioDTO.class);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    public double obtenerConsumo(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        return solicitudIARepository.findByUsuario(usuario)
                .stream()
                .mapToDouble(SolicitudIA::getCostoCalculado)
                .sum();
    }
}