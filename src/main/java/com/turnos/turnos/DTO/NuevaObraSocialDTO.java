package com.turnos.turnos.DTO;

import com.turnos.turnos.model.Plan;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevaObraSocialDTO {
    
    private String nombre;
    private String direccion;
    private Set<Plan> planes;

    public NuevaObraSocialDTO(String nombre, String direccion, Set<Plan> planes) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.planes = planes;
    }

    public NuevaObraSocialDTO() {
    }
    
}
