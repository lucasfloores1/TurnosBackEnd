package com.turnos.turnos.DTO;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPacienteDTO {
    
    private Long id;
    private String nombre;
    private String mail;
    private Long dni;
    private String direccion;
    private String tel;
    
    private List<ObraSocialDTO> obrasSociales;

    public GetPacienteDTO() {
    }

    public GetPacienteDTO(Long id, String nombre, String mail, Long dni, String direccion, String tel, List<ObraSocialDTO> obrasSociales) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.dni = dni;
        this.direccion = direccion;
        this.tel = tel;
        this.obrasSociales = obrasSociales;
    }
    
}
