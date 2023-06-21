package com.turnos.turnos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoUserDTO {
    
    private Long id;
    private String nombre;
    private String tel;
    private String email;
    private String username;
    private String password;

    public NuevoUserDTO() {
    }

    public NuevoUserDTO(Long id, String nombre, String tel, String email, String username, String password) {
        this.id = id;
        this.nombre = nombre;
        this.tel = tel;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    
}
