package com.example.pc_piatto.application;

import com.example.pc_piatto.Service.ModeloIAService;
import com.example.pc_piatto.dto.ModeloIADTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class ModeloIAController {
    @Autowired
    private ModeloIAService modeloIAService;

    @GetMapping("/models")
    public List<ModeloIADTO> modelosDisponibles(@RequestParam Long usuarioId) {
        return modeloIAService.obtenerModelosPermitidos(usuarioId);
    }
}