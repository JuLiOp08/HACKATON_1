package com.example.pc_piatto.dto;

import java.util.Map;

public class CrearPedidoDto {
    private Map<String, Integer> cantidadPorPlato;

    public Map<String, Integer> getCantidadPorPlato() {
        return cantidadPorPlato;
    }

    public void setCantidadPorPlato(Map<String, Integer> cantidadPorPlato) {
        this.cantidadPorPlato = cantidadPorPlato;
    }
}
