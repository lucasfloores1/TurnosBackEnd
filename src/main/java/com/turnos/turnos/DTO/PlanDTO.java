package com.turnos.turnos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanDTO {
    
    private Long id;
    private String nombre;

    public PlanDTO() {
    }

    public PlanDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
}
