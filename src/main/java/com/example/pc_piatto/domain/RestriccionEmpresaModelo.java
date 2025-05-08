package com.example.pc_piatto.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestriccionEmpresaModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private ModeloIA modelo;

    @Enumerated(EnumType.STRING)
    private TipoLimite tipoLimite;

    private double valorMaximo;
}
