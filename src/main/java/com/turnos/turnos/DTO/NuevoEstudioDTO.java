package com.turnos.turnos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoEstudioDTO {
    
    private Long userId;
    private String nombre;
    private String nomenclador;

    public NuevoEstudioDTO() {
    }

    public NuevoEstudioDTO(Long userId, String nombre, String nomenclador) {
        this.userId = userId;
        this.nombre = nombre;
        this.nomenclador = nomenclador;
    }
    
}
