package com.turnos.turnos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorarioDTO {
    
    private Long id;
    private int dia;
    private String inicio;
    private String fin;
    private int intervalo;

    public HorarioDTO() {
    }

    public HorarioDTO(Long id, int dia, String inicio, String fin, int intervalo) {
        this.id = id;
        this.dia = dia;
        this.inicio = inicio;
        this.fin = fin;
        this.intervalo=intervalo;
    }
    
}
