package com.turnos.turnos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Estudio {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id ;
    
    @Column ( name = "nombre", length = 100, nullable = false )
    private String nombre;
    
    @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "estudio" )
    private Set<Turno> turnos = new HashSet<>();

    public Estudio() {
    }

    public Estudio(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
}
