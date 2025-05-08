package com.example.pc_piatto.Service;

import com.example.pc_piatto.Enum.UsuarioRol;
import com.example.pc_piatto.domain.LimiteUsuario;
import com.example.pc_piatto.domain.Usuario;
import com.example.pc_piatto.dto.UsuarioDTO;
import com.example.pc_piatto.repository.LimiteUsuarioRepository;
import com.example.pc_piatto.repository.SolicitudIARepository;
import com.example.pc_piatto.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository UsuarioRepository;
    private final LimiteUsuarioRepository LimiteUsuarioRepository;
    private final SolicitudIARepository SolicitudIARepository;

    public UsuarioService(UsuarioRepository UsuarioRepository,
                          LimiteUsuarioRepository LimiteUsuarioRepository,
                          SolicitudIARepository SolicitudIARepository) {
        this.UsuarioRepository = UsuarioRepository;
        this.LimiteUsuarioRepository = LimiteUsuarioRepository;
        this.SolicitudIARepository = SolicitudIARepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setEnable(true);
        return UsuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerUsuariosPorEmpresa(Long empresaId) {
        return UsuarioRepository.findByEmpresaId(empresaId);
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return UsuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Long id, Usuario datosActualizados) {
        Usuario usuario = UsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(datosActualizados.getNombre());
        usuario.setEmail(datosActualizados.getEmail());
        return UsuarioRepository.save(usuario);
    }

    public void asignarLimiteUsuario(Long usuarioId, LimiteUsuario limite) {
        Usuario usuario = UsuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        limite.setUsuario(usuario);
        LimiteUsuarioRepository.save(limite);
    }

    public UsuarioDTO obtenerConsumo(Long usuarioId) {
        Usuario usuario = UsuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        int totalSolicitudes = Math.toIntExact(SolicitudIARepository.countByUsuarioId(usuarioId));
        int totalTokens = SolicitudIARepository.sumTokensByUsuarioId(usuarioId);

        return new UsuarioDTO(usuarioId, totalSolicitudes, totalTokens);
    }


    public void verificarEmpresa(Authentication auth, Long empresaId) {
        Usuario actual = (Usuario) UsuarioRepository.findByCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!actual.getRol().equals(UsuarioRol.ROLE_COMPANY_ADMIN)) {
            throw new RuntimeException("No autorizado.");
        }

        if (!actual.getEmpresa().getId().equals(empresaId)) {
            throw new RuntimeException("Acceso denegado a esta empresa.");
        }
    }


    public void verificarAccesoUsuario(Authentication auth, Long usuarioId) {
        Usuario actual = (Usuario) UsuarioRepository.findByCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Usuario objetivo = UsuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario destino no encontrado"));

        if (!actual.getRol().equals(UsuarioRol.ROLE_COMPANY_ADMIN)) {
            throw new RuntimeException("No autorizado.");
        }

        if (!actual.getEmpresa().getId().equals(objetivo.getEmpresa().getId())) {
            throw new RuntimeException("Acceso denegado a usuarios fuera de su empresa.");
        }
    }

    public void verificarAccesoConsumo(Authentication auth, Long usuarioId) {
        Usuario actual = (Usuario) UsuarioRepository.findByCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (actual.getRol().equals(UsuarioRol.ROLE_USER) && !actual.getId().equals(usuarioId)) {
            throw new RuntimeException("No puede ver el consumo de otro usuario.");
        }

        if (actual.getRol().equals(UsuarioRol.ROLE_COMPANY_ADMIN)) {
            Usuario destino = UsuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario destino no encontrado"));

            if (!actual.getEmpresa().getId().equals(destino.getEmpresa().getId())) {
                throw new RuntimeException("No autorizado para ver el consumo de este usuario.");
            }
        }
    }
}