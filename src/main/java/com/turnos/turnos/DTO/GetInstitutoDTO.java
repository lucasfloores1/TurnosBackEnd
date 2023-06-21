package com.turnos.turnos.DTO;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetInstitutoDTO {
    
    private Long id;
    private String nombre;
    private String direccion;
    private String cuit;
    private List<GetMedicoDTO> medicos;

    public GetInstitutoDTO() {
    }

    public GetInstitutoDTO(Long id, String nombre, String direccion, String cuit, List<GetMedicoDTO> medicos) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuit = cuit;
        this.medicos = medicos;
    }
    
    
    
}
