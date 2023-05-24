package com.turnos.turnos.DTO;

import com.turnos.turnos.model.Horario;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoMedicoDTO {
    
    private Long id;
    private String nombre;
    private String mail;
    private Long dni;
    private String direccion;
    private int matricula;
    private Long idInstituto;
    private HashSet<Horario> horarios;

    public NuevoMedicoDTO() {
    }

    public NuevoMedicoDTO(Long id, String nombre, String mail, Long dni, String direccion, int matricula, Long idInstituto, HashSet<Horario> horarios) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.dni = dni;
        this.direccion = direccion;
        this.matricula = matricula;
        this.idInstituto = idInstituto;
        this.horarios = horarios;
    }
    
    
    
}
