package com.turnos.turnos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoUserDTO {
    
    private String nombre;
    private String tel;
    private String email;
    private String username;
    private String password;

    public NuevoUserDTO() {
    }

    public NuevoUserDTO(String nombre, String tel, String email, String username, String password) {
        this.nombre = nombre;
        this.tel = tel;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    
}
