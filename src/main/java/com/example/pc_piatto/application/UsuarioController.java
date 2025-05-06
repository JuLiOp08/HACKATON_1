package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.UsuarioService;
import com.example.pc_piatto.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> listarTodos() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuario(id);
    }

    @PostMapping
    public UsuarioDTO crear(@RequestBody UsuarioDTO dto) {
        return usuarioService.crearUsuario(dto);
    }

    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        return usuarioService.actualizarUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}
