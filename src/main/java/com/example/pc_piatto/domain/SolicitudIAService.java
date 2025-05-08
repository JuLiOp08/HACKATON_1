package com.example.pc_piatto.domain;

import com.example.pc_piatto.repository.SolicitudIARepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class SolicitudIAService {

    private final SolicitudIARepository SolicitudIARepository;

    public SolicitudIAService(SolicitudIARepository SolicitudIARepository) {
        this.SolicitudIARepository = SolicitudIARepository;
    }

    public SolicitudIA registrarSolicitudIA(SolicitudIA SolicitudIA) {
        SolicitudIA.setFechaHora(ZonedDateTime.now());
        return SolicitudIARepository.save(SolicitudIA);
    }

    public List<SolicitudIA> obtenerHistorialPorUsuario(Long usuarioId) {
        return SolicitudIARepository.findByUsuarioId(usuarioId);
    }
}