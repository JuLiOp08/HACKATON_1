package com.example.pc_piatto.application;

import com.example.pc_piatto.application.UsuarioService;
import com.example.pc_piatto.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDto> listarTodos() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDto obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuario(id);
    }

    @PostMapping
    public UsuarioDto crear(@RequestBody UsuarioDto dto) {
        return usuarioService.crearUsuario(dto);
    }

    @PutMapping("/{id}")
    public UsuarioDto actualizar(@PathVariable Long id, @RequestBody UsuarioDto dto) {
        return usuarioService.actualizarUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}
