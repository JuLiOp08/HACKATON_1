package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.SolicitudIA;
import com.example.pc_piatto.Service.SolicitudIAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class SolicitudIAController {

    private final SolicitudIAService SolicitudIAService;

    public SolicitudIAController(SolicitudIAService SolicitudIAService) {
        this.SolicitudIAService = SolicitudIAService;
    }

    @PostMapping("/chat")
    public ResponseEntity<SolicitudIA> crearChat(@RequestBody SolicitudIA SolicitudIA) {
        return ResponseEntity.ok(SolicitudIAService.registrarSolicitudIA(SolicitudIA));
    }

    @PostMapping("/completion")
    public ResponseEntity<SolicitudIA> crearCompletion(@RequestBody SolicitudIA SolicitudIA) {
        return ResponseEntity.ok(SolicitudIAService.registrarSolicitudIA(SolicitudIA));
    }

    @PostMapping("/multimodal")
    public ResponseEntity<SolicitudIA> crearMultimodal(@RequestBody SolicitudIA SolicitudIA) {
        return ResponseEntity.ok(SolicitudIAService.registrarSolicitudIA(SolicitudIA));
    }

    @GetMapping("/history")
    public ResponseEntity<List<SolicitudIA>> historialUsuario(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(SolicitudIAService.obtenerHistorialPorUsuario(usuarioId));
    }
}