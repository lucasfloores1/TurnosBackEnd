package com.turnos.turnos.DTO;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMedicoDTO {
    
    private Long id;
    private String nombre;
    private String mail;
    private Long dni;
    private String direccion;
    private String tel;
    private int matricula;
    
    private List<InstitutoDTO> institutos;

    public GetMedicoDTO() {
    }

    public GetMedicoDTO(Long id, String nombre, String mail, Long dni, String direccion, String tel, int matricula, List<InstitutoDTO> institutos) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.dni = dni;
        this.direccion = direccion;
        this.tel = tel;
        this.matricula = matricula;
        this.institutos = institutos;
    }
    
}
