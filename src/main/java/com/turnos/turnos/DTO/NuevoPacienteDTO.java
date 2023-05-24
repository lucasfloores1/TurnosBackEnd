package com.turnos.turnos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoPacienteDTO {
    
    private Long id;
    private String nombre;
    private String mail;
    private Long dni;
    private String direccion;
    private Long idObraSocial;
    private Long idPlan;
    private String afiliado;

    public NuevoPacienteDTO() {
    }

    public NuevoPacienteDTO(Long id, String nombre, String mail, Long dni, String direccion, Long idObraSocial, Long idPlan, String afiliado) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.dni = dni;
        this.direccion = direccion;
        this.idObraSocial = idObraSocial;
        this.idPlan = idPlan;
        this.afiliado = afiliado;
    }
    
    
    
}
