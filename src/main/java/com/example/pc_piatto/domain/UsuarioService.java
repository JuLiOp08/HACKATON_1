package com.example.pc_piatto.domain;

import com.example.pc_piatto.repository.UsuarioRepository;
import org.springframework.stereotype.Service;



import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario patchUsuario(Usuario usuario, Long usuario_id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuario_id);

        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con ID: " + usuario.getId());
        }

        Usuario existente = optionalUsuario.get();

        if (usuario.getNombreDeUsuario() != null) {
            existente.setNombreDeUsuario(usuario.getNombreDeUsuario());
        }

        if (usuario.getCorreo() != null) {
            existente.setCorreo(usuario.getCorreo());
        }

        if (usuario.getPassword() != null) {
            existente.setPassword(usuario.getPassword());
        }

        return usuarioRepository.save(existente);
    }
}
