package com.example.pc_piatto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Estado estado = Estado.PREPARANDO;

    @ElementCollection
    @CollectionTable(name = "pedido_cantidad_por_plato", joinColumns = @JoinColumn(name = "pedido_id"))
    @MapKeyColumn(name = "plato")
    @Column(name = "cantidad")
    private Map<String, Integer> cantidadPorPlato = new HashMap<>();

    @ManyToOne
    private Usuario usuario;

    @ManyToMany(mappedBy = "pedidos")
    private List<Plato> platos;

}
