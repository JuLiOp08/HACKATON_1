package com.example.pc_piatto.domain;

import com.example.pc_piatto.dto.CrearPedidoDto;
import com.example.pc_piatto.repository.PedidoRepository;
import com.example.pc_piatto.repository.PlatoRepository;
import com.example.pc_piatto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PlatoRepository platoRepository;

    public Pedido crearPedido(CrearPedidoDto dto, String username) {
        Usuario usuario = usuarioRepository.findByNombreDeUsuario(username);

        Map<String, Integer> cantidadPorPlato = dto.getCantidadPorPlato();

        List<Plato> platos = platoRepository.findByNombreIn(new ArrayList<>(cantidadPorPlato.keySet()));

        if (platos.size() != cantidadPorPlato.size()) {
            throw new RuntimeException("Uno o más platos no existen en el sistema.");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setCantidadPorPlato(cantidadPorPlato);
        pedido.setPlatos(platos);

        return pedidoRepository.save(pedido);
    }


    public List<Pedido> pedidosRealizados(Long usuario_id) {
        return pedidoRepository.findByUsuarioId(usuario_id);
    }

    public Pedido cancelarPedido(Long pedido_id) {
        Optional<Pedido> pedido = pedidoRepository.findById(pedido_id);

        if (pedido.isEmpty())
            return null;

        Pedido pedidoObtenido = pedido.get();

        if (pedidoObtenido.getEstado() != Estado.PREPARANDO)
            return null; //no se puede cancelar pedido que no está en preparando

        pedidoObtenido.setEstado(Estado.CANCELADO);

        return pedidoRepository.save(pedidoObtenido);


    }


}
