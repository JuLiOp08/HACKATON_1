package com.example.pc_piatto.domain;

import com.example.pc_piatto.repository.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatoService {

    @Autowired
    PlatoRepository platoRepository;


    public List<Plato> getPlatos() {
        return platoRepository.findAll();
    }

    public Plato getPlato(Long plato_id) {
        return platoRepository.findById(plato_id).orElseThrow();
    }

    public List<Plato> menuDelDia() {
        return platoRepository.findAllByEnMenuDelDia(true);
    }

}
