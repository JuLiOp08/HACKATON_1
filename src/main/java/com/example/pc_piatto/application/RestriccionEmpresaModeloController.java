package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.RestriccionEmpresaModeloService;
import com.example.pc_piatto.dto.RestriccionEmpresaModeloDTO;
import com.example.pc_piatto.dto.RestriccionEmpresaModeloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/restrictions")
public class RestriccionEmpresaModeloController {

    @Autowired
    private RestriccionEmpresaModeloService service;

    @PostMapping
    public RestriccionEmpresaModeloDTO crear(@RequestBody RestriccionEmpresaModeloDTO DTO) {
        return service.crearRestriccion(DTO);
    }

    @GetMapping
    public List<RestriccionEmpresaModeloDTO> listar() {
        return service.listarRestricciones();
    }

    @PutMapping("/{id}")
    public RestriccionEmpresaModeloDTO actualizar(@PathVariable Long id, @RequestBody RestriccionEmpresaModeloDTO DTO) {
        return service.actualizarRestriccion(id, DTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarRestriccion(id);
    }
}
