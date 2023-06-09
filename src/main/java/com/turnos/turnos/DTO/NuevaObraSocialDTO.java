package com.turnos.turnos.DTO;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevaObraSocialDTO {
    
    private Long userId;
    private Long id;
    private String nombre;
    private String direccion;
    private Set<PlanDTO> planes;

    public NuevaObraSocialDTO(Long userId, Long id, String nombre, String direccion, Set<PlanDTO> planes) {
        this.userId = userId;
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.planes = planes;
    }

    public NuevaObraSocialDTO() {
    }
    
}
