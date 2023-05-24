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
public class ObraSocial {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id ;
    
    @Column ( name = "nombre", length = 50, nullable = false )
    private String nombre;
    
    @Column ( name = "direccion", length = 100, nullable = false )
    private String direccion;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "obraSocial" )
    private Set<Paciente_ObraSocial> pacienteObraSocial = new HashSet<>();
    
    @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "obraSocial" )
    private Set<Turno> turnos = new HashSet<>();
    
    @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "obraSocial"  )
    private Set<Plan> planes = new HashSet<>();

    public ObraSocial(Long id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public ObraSocial() {
    }
}
