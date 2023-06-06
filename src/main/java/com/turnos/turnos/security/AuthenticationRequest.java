package com.turnos.turnos.security;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String email;
    private String username;
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
    
}
