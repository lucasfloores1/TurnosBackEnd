package com.turnos.turnos.DTO;

import com.turnos.turnos.model.Horario;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoMedicoDTO {
     
    private String nombre;
    private String mail;
    private Long dni;
    private String direccion;
    private String tel;
    private int matricula;
    private Long instituto;
    private Set<Horario> horarios;

    public NuevoMedicoDTO() {
    }

    public NuevoMedicoDTO(String nombre, String mail, Long dni, String direccion, String tel, int matricula, Long instituto, Set<Horario> horarios) {
        this.nombre = nombre;
        this.mail = mail;
        this.dni = dni;
        this.direccion = direccion;
        this.tel = tel;
        this.matricula = matricula;
        this.instituto = instituto;
        this.horarios = horarios;
    }




    
    
}
