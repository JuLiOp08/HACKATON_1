package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.LimiteUsuarioModeloService;
import com.example.pc_piatto.dto.LimiteUsuarioModeloDTO;
import com.example.pc_piatto.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.pc_piatto.domain.UsuarioService;

import java.util.List;


@RestController
@RequestMapping("/api/company/users")
public class LimiteUsuarioModeloController {

    @Autowired
    private LimiteUsuarioModeloService limiteService;

    @PostMapping
    public LimiteUsuarioModeloDTO crear(@RequestBody LimiteUsuarioModeloDTO dto) {
        return limiteService.crear(dto);
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return UsuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtener(@PathVariable Long id) {
        return UsuarioService.obtenerUsuario(id);
    }

    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        return UsuarioService.actualizarUsuario(id, dto);
    }

    @PostMapping("/{id}/limits")
    public LimiteUsuarioModeloDTO asignarLimite(@PathVariable Long id, @RequestBody LimiteUsuarioModeloDTO dto) {
        return limiteService.asignarLimite(id, dto);
    }

    @GetMapping("{id}/consumption")
    public double obtenerLimite(@PathVariable Long id, @RequestBody LimiteUsuarioModeloDTO dto) {
        return UsuarioService.obtenerConsumo(id, dto);
    }

}