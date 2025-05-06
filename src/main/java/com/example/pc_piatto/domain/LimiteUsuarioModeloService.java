package com.example.pc_piatto.domain;

import com.example.pc_piatto.domain.LimiteUsuarioModelo;
import com.example.pc_piatto.domain.ModeloIA;
import com.example.pc_piatto.domain.Usuario;
import com.example.pc_piatto.dto.LimiteUsuarioModeloDTO;
import com.example.pc_piatto.repository.LimiteUsuarioModeloRepository;
import com.example.pc_piatto.repository.ModeloIARepository;
import com.example.pc_piatto.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimiteUsuarioModeloService {

    @Autowired
    private LimiteUsuarioModeloRepository limiteRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private ModeloIARepository modeloRepo;

    @Autowired
    private ModelMapper modelMapper;

    public LimiteUsuarioModeloDTO asignarLimite(Long usuarioId, LimiteUsuarioModeloDTO dto) {
        Usuario usuario = usuarioRepo.findById(usuarioId).orElseThrow();
        ModeloIA modelo = modeloRepo.findById(dto.getModeloId()).orElseThrow();

        LimiteUsuarioModelo limite = new LimiteUsuarioModelo();
        limite.setUsuario(usuario);
        limite.setModelo(modelo);
        limite.setVentanaTiempo(dto.getVentanaTiempo());
        limite.setValorMaximo(dto.getValorMaximo());

        return modelMapper.map(limiteRepo.save(limite), LimiteUsuarioModeloDTO.class);
    }
}
