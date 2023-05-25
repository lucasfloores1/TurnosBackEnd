package com.turnos.turnos.DTO;

import com.turnos.turnos.model.Horario;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutoDTO {
    
    private Long id;
    private String nombre;
    private String direccion;
    
    private List<HorarioDTO> horarios;

    public InstitutoDTO() {
    }

    public InstitutoDTO(Long id, String nombre, String direccion, List<HorarioDTO> horarios) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.horarios = horarios;
    }

}
