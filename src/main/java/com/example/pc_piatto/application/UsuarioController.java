package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.UsuarioService;
import com.example.pc_piatto.dto.LimiteUsuarioModeloDTO;
import com.example.pc_piatto.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> listarTodos() {
        return UsuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtenerPorId(@PathVariable Long id) {
        return UsuarioService.obtenerUsuario(id);
    }

    @PostMapping
    public UsuarioDTO crear(@RequestBody UsuarioDTO dto) {
        return usuarioService.crearUsuario(dto);
    }

    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        return UsuarioService.actualizarUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }

    @GetMapping("/{id}/consumption")
    public double consumo(@PathVariable Long id, @RequestBody LimiteUsuarioModeloDTO dto) {
        return UsuarioService.obtenerConsumo(id, dto);
    }
}
