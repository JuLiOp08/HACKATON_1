package com.example.pc_piatto.domain;

import com.example.pc_piatto.dto.UsuarioDTO;
import com.example.pc_piatto.repository.EmpresaRepository;
import com.example.pc_piatto.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private static UsuarioRepository usuarioRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private static ModelMapper modelMapper;

    public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        Empresa empresa = empresaRepo.findById(dto.getEmpresaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada"));

        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setEmpresa(empresa);

        Usuario guardado = usuarioRepo.save(usuario);
        return modelMapper.map(guardado, UsuarioDTO.class);
    }

    public static List<UsuarioDTO> listarUsuarios() {
        return usuarioRepo.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public static UsuarioDTO obtenerUsuario(Long id) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public static UsuarioDTO actualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());

        Usuario actualizado = usuarioRepo.save(usuario);
        return modelMapper.map(actualizado, UsuarioDTO.class);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }

}
