package com.turnos.turnos.DTO;

import com.turnos.turnos.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLoginDTO {
    
    private String token;
    private User user;
    private boolean valid;

    public AuthLoginDTO() {
    }

    public AuthLoginDTO(String token, User user, boolean valid) {
        this.token = token;
        this.user = user;
        this.valid = valid;
    }
    
}
