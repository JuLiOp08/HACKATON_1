package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.LimiteUsuario;
import com.example.pc_piatto.domain.Usuario;
import com.example.pc_piatto.domain.UsuarioService;
import com.example.pc_piatto.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/company/users")
public class UsuarioController {

    private final UsuarioService servicioUsuario;

    public UsuarioController(UsuarioService servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario nuevoUsuario, Authentication autenticacion) {
        servicioUsuario.verificarEmpresa(autenticacion, nuevoUsuario.getEmpresa().getId());
        Usuario usuarioGuardado = servicioUsuario.crearUsuario(nuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(@RequestParam Long idEmpresa, Authentication autenticacion) {
        servicioUsuario.verificarEmpresa(autenticacion, idEmpresa);
        List<Usuario> usuarios = servicioUsuario.obtenerUsuariosPorEmpresa(idEmpresa);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id, Authentication autenticacion) {
        servicioUsuario.verificarAccesoUsuario(autenticacion, id);
        return servicioUsuario.buscarUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario datosActualizados, Authentication autenticacion) {
        servicioUsuario.verificarEmpresa(autenticacion, id);
        Usuario usuarioActualizado = servicioUsuario.actualizarUsuario(id, datosActualizados);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @PostMapping("/{id}/limits")
    public ResponseEntity<String> establecerLimite(@PathVariable Long id, @RequestBody Limite limiteAsignado, Authentication autenticacion) {
        servicioUsuario.verificarAccesoUsuario(autenticacion, id);
        servicioUsuario.asignarLimiteUsuario(id, limiteAsignado);
        return ResponseEntity.ok("Límite asignado exitosamente al usuario.");
    }

    @GetMapping("/{id}/consumption")
    public ResponseEntity<UsuarioDTO> consultarConsumo(@PathVariable Long id, Authentication autenticacion) {
        servicioUsuario.verificarAccesoConsumo(autenticacion, id);
        UsuarioDTO consumoUsuario = servicioUsuario.obtenerConsumo(id);
        return ResponseEntity.ok(consumoUsuario);
    }
}


