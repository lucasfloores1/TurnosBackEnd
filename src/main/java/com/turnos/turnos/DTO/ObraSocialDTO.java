package com.turnos.turnos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObraSocialDTO {
    
    private Long id;
    private String nombre;
    private String direccion;
    
    private PlanDTO plan;
    
    private String afiliado;

    public ObraSocialDTO() {
    }

    public ObraSocialDTO(Long id, String nombre, String direccion, PlanDTO plan, String afiliado) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.plan = plan;
        this.afiliado = afiliado;
    }
    
}
