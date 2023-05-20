package com.turnos.turnos.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Persona {
    
    @Column ( name = "nombre" , length = 50, nullable = false )
    private String nombre;
    
    @Column ( name = "dni" , nullable = false )
    private Long dni;

    @Column ( name = "tel" , length = 50, nullable = false )
    private String tel;
    
    @Column ( name = "mail" , length = 50, nullable = false )
    private String mail;

    @Column ( name = "direccion" , length = 100 )
    private String direccion;

    public Persona() {
    }

    public Persona(String nombre, Long dni, String tel, String mail, String direccion) {
        this.nombre = nombre;
        this.dni = dni;
        this.tel = tel;
        this.mail = mail;
        this.direccion = direccion;
    }
    
}
