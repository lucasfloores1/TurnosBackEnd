package com.turnos.turnos.DTO;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoPacienteDTO {
    
    private Long userId;
    private Long id;
    private String nombre;
    private String mail;
    private Long dni;
    private String tel;
    private String direccion;
    private List<PacienteOSDTO> obrasSociales;

    public NuevoPacienteDTO() {
    }

    public NuevoPacienteDTO(Long userId, Long id, String nombre, String mail, Long dni, String tel, String direccion, List<PacienteOSDTO> obrasSociales) {
        this.userId = userId;
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.dni = dni;
        this.tel = tel;
        this.direccion = direccion;
        this.obrasSociales = obrasSociales;
    }    
    
}
