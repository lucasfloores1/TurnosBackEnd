package com.turnos.turnos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteOSDTO {
    
    private Long idObraSocial;
    private Long idPlan;
    private String afiliado;

    public PacienteOSDTO() {
    }

    public PacienteOSDTO(Long idObraSocial, Long idPlan, String afiliado) {
        this.idObraSocial = idObraSocial;
        this.idPlan = idPlan;
        this.afiliado = afiliado;
    }
    
}
