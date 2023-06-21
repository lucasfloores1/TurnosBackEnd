package com.turnos.turnos.DTO;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetObraSocialDTO {
    
    private Long id;
    private String nombre;
    private String direccion;
    
    private Set<PlanDTO> planes;

    public GetObraSocialDTO(Long id, String nombre, String direccion, Set<PlanDTO> planes) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.planes = planes;
    }

    public GetObraSocialDTO() {
    }
    
    
}
