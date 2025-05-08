package com.example.pc_piatto.domain;

import com.example.pc_piatto.dto.SolicitudIADTO;
import com.example.pc_piatto.repository.ModeloIARepository;
import com.example.pc_piatto.repository.SolicitudIARepository;
import com.example.pc_piatto.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudIAService {

    @Autowired
    private SolicitudIARepository solicitudRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private ModeloIARepository modeloRepo;

    @Autowired
    private ModelMapper modelMapper;

    public SolicitudIADTO procesarSolicitud(Long usuarioId, Long modeloId, String consulta, String tipo) {
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        ModeloIA modelo = modeloRepo.findById(modeloId)
                .orElseThrow(() -> new RuntimeException("Modelo no encontrado"));

        // Simulación de procesamiento IA (reemplaza con llamada real si aplica)
        int tokensConsumidos = consulta.length() / 4 + 1;
        double costo = tokensConsumidos * modelo.getCostoPorToken();

        SolicitudIA solicitud = SolicitudIA.builder()
                .usuario(usuario)
                .modelo(modelo)
                .fechaHora(LocalDateTime.now())
                .consulta(consulta)
                .respuesta("[RESPUESTA SIMULADA de tipo: " + tipo + "]")
                .tokensConsumidos(tokensConsumidos)
                .costoCalculado(costo)
                .build();

        return modelMapper.map(solicitudRepo.save(solicitud), SolicitudIADTO.class);
    }

    public List<SolicitudIADTO> historialDeUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return solicitudRepo.findByUsuario(usuario).stream()
                .map(s -> modelMapper.map(s, SolicitudIADTO.class))
                .collect(Collectors.toList());
    }
}