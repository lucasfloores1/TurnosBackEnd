package com.turnos.turnos.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoTurnoDTO {
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;
    
    private boolean confirmado;
    private boolean cargado;
    private Long idMedico;
    private Long idInstituto;
    private Long idPaciente;
    private Long idObraSocial;
    private Long idPlan;
    private Long idEstudio;

    public NuevoTurnoDTO() {
    }

    public NuevoTurnoDTO(LocalDateTime fecha, boolean confirmado, boolean cargado, Long idMedico, Long idInstituto, Long idPaciente, Long idObraSocial, Long idPlan, Long idEstudio) {
        this.fecha = fecha;
        this.confirmado = confirmado;
        this.cargado = cargado;
        this.idMedico = idMedico;
        this.idInstituto = idInstituto;
        this.idPaciente = idPaciente;
        this.idObraSocial = idObraSocial;
        this.idPlan = idPlan;
        this.idEstudio = idEstudio;
    }
    
}
