package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.SolicitudIAService;
import com.example.pc_piatto.dto.SolicitudIADTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class SolicitudIAController {

    @Autowired
    private SolicitudIAService solicitudService;

    @PostMapping("/chat")
    public SolicitudIADTO usarChat(@RequestParam Long usuarioId,
                                   @RequestParam Long modeloId,
                                   @RequestBody String consulta) {
        return solicitudService.procesarSolicitud(usuarioId, modeloId, consulta, "chat");
    }

    @PostMapping("/completion")
    public SolicitudIADTO usarCompletion(@RequestParam Long usuarioId,
                                         @RequestParam Long modeloId,
                                         @RequestBody String consulta) {
        return solicitudService.procesarSolicitud(usuarioId, modeloId, consulta, "completion");
    }

    @PostMapping("/multimodal")
    public SolicitudIADTO usarMultimodal(@RequestParam Long usuarioId,
                                         @RequestParam Long modeloId,
                                         @RequestBody String consulta) {
        return solicitudService.procesarSolicitud(usuarioId, modeloId, consulta, "multimodal");
    }

    @GetMapping("/history")
    public List<SolicitudIADTO> historial(@RequestParam Long usuarioId) {
        return solicitudService.historialDeUsuario(usuarioId);
    }
}
