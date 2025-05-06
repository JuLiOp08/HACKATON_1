package com.example.pc_piatto.domain;
package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.Usuario;
import com.example.pc_piatto.dto.UsuarioDTO;
import com.example.pc_piatto.repository.EmpresaRepository;
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

    public List<UsuarioDto> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDto.class))
                .collect(Collectors.toList());
    }

    public UsuarioDto obtenerUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    public UsuarioDto crearUsuario(UsuarioDto dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setEmpresa(empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada")));
        Usuario guardado = usuarioRepository.save(usuario);
        return modelMapper.map(guardado, UsuarioDto.class);
    }

    public UsuarioDto actualizarUsuario(Long id, UsuarioDto dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());
        Usuario actualizado = usuarioRepository.save(usuario);
        return modelMapper.map(actualizado, UsuarioDto.class);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}