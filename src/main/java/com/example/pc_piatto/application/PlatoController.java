package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.Plato;
import com.example.pc_piatto.domain.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plato")
public class PlatoController {

    @Autowired
    PlatoService platoService;

    @GetMapping
    ResponseEntity<List<Plato>> getPlatos() {
        return ResponseEntity.ok(platoService.getPlatos());
    }

    @GetMapping("/{plato_id}")
    ResponseEntity<Plato> getPlato(@PathVariable Long plato_id) {
        return ResponseEntity.ok(platoService.getPlato(plato_id));
    }


    @GetMapping("/menu_del_dia")
    ResponseEntity<List<Plato>> menuDelDia() {
        return ResponseEntity.ok(platoService.menuDelDia());
    }


}
