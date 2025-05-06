package com.example.pc_piatto.application;

import com.example.pc_piatto.application.RestriccionEmpresaModeloService;
import com.example.pc_piatto.dto.RestriccionEmpresaModeloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/restrictions")
public class RestriccionEmpresaModeloController {

    @Autowired
    private RestriccionEmpresaModeloService service;

    @PostMapping
    public RestriccionEmpresaModeloDto crear(@RequestBody RestriccionEmpresaModeloDto dto) {
        return service.crearRestriccion(dto);
    }

    @GetMapping
    public List<RestriccionEmpresaModeloDto> listar() {
        return service.listarRestricciones();
    }

    @PutMapping("/{id}")
    public RestriccionEmpresaModeloDto actualizar(@PathVariable Long id, @RequestBody RestriccionEmpresaModeloDto dto) {
        return service.actualizarRestriccion(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarRestriccion(id);
    }
}
