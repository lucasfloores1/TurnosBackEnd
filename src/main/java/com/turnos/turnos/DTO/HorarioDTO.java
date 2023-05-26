package com.turnos.turnos.DTO;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorarioDTO {
    
    private Long id;
    private int dia;
    private LocalTime inicio;
    private LocalTime fin;
    private int intervalo;

    public HorarioDTO() {
    }

    public HorarioDTO(Long id, int dia, LocalTime inicio, LocalTime fin, int intervalo) {
        this.id = id;
        this.dia = dia;
        this.inicio = inicio;
        this.fin = fin;
        this.intervalo=intervalo;
    }
    
}
