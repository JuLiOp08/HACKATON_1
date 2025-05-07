package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.EmpresaService;
import com.example.pc_piatto.dto.EmpresaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public EmpresaDTO crear(@RequestBody EmpresaDTO dto) {
        return empresaService.crearEmpresa(dto);
    }

    @GetMapping
    public List<EmpresaDTO> listar() {
        return empresaService.listarEmpresas();
    }

    @GetMapping("/{id}")
    public EmpresaDTO obtenerPorId(@PathVariable Long id) {
        return empresaService.obtenerEmpresaPorId(id);
    }

    @PutMapping("/{id}")
    public EmpresaDTO actualizar(@PathVariable Long id, @RequestBody EmpresaDTO dto) {
        return empresaService.actualizarEmpresa(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        empresaService.eliminarEmpresa(id);
    }

    @PatchMapping("/{id}/status")
    public EmpresaDTO activarDesactivar(@PathVariable Long id, @RequestBody EmpresaDTO dto) {
        return empresaService.activarDesactivarEmpresa(id, dto.isEstadoActivo());
    }

    @GetMapping("/{id}/consumption")
    public String obtenerConsumo(@PathVariable Long id) {
        return empresaService.obtenerConsumoEmpresa(id);
    }
}
