package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.LimiteUsuarioModeloService;
import com.example.pc_piatto.dto.LimiteUsuarioModeloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company/users")
public class LimiteUsuarioModeloController {

    @Autowired
    private LimiteUsuarioModeloService limiteService;

    @PostMapping
    public LimiteUsuarioModeloDTO crear(@RequestBody LimiteUsuarioModeloDTO dto) {
        return limiteService.crear(dto);
    }


    @PostMapping("/{id}/limits")
    public LimiteUsuarioModeloDTO asignarLimite(@PathVariable Long id,
                                                @RequestBody LimiteUsuarioModeloDTO dto) {
        return limiteService.asignarLimite(id, dto);
    }


}