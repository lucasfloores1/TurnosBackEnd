package com.turnos.turnos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoInstitutoDTO {
    
    private Long userId;
    private String nombre;
    private String direccion;
    private String cuit;

    public NuevoInstitutoDTO() {
    }

    public NuevoInstitutoDTO(Long userId, String nombre, String direccion, String cuit) {
        this.userId = userId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuit = cuit;
    }

}
