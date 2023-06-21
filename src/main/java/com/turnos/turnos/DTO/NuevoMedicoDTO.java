package com.turnos.turnos.DTO;

import com.turnos.turnos.model.Horario;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoMedicoDTO {
    
    private Long id;
    private Long userId;     
    private String nombre;
    private String mail;
    private Long dni;
    private String direccion;
    private String tel;
    private int matricula;
    private Long idInstituto;
    private Set<Horario> horarios;

    public NuevoMedicoDTO() {
    }

    public NuevoMedicoDTO(Long id, Long userId, String nombre, String mail, Long dni, String direccion, String tel, int matricula, Long idInstituto, Set<Horario> horarios) {
        this.id = id;
        this.userId = userId;
        this.nombre = nombre;
        this.mail = mail;
        this.dni = dni;
        this.direccion = direccion;
        this.tel = tel;
        this.matricula = matricula;
        this.idInstituto = idInstituto;
        this.horarios = horarios;
    }
    
}
