package com.example.pc_piatto.application;

import com.example.pc_piatto.domain.Pedido;
import com.example.pc_piatto.domain.PedidoService;
import com.example.pc_piatto.dto.CrearPedidoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody CrearPedidoDto dto, Principal principal) {
        System.out.println("Usuario autenticado: " + principal.getName());
        Pedido pedido = pedidoService.crearPedido(dto, principal.getName());
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/{usuario_id}")
    ResponseEntity<List<Pedido>> pedidosRealizados(@PathVariable Long usuario_id) {
        return ResponseEntity.ok(pedidoService.pedidosRealizados(usuario_id));
    }

    @DeleteMapping("/{pedido_id}")
    ResponseEntity<Pedido> cancelarPedido(@PathVariable Long pedido_id) {
        return ResponseEntity.ok(pedidoService.cancelarPedido(pedido_id));
    }

}
