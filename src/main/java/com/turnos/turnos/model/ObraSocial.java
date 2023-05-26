package com.turnos.turnos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class ObraSocial {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id ;
    
    @Column ( name = "nombre", length = 50, nullable = false )
    private String nombre;
    
    @Column ( name = "direccion", length = 100)
    private String direccion;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "obraSocial" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Paciente_ObraSocial> pacienteObraSocial = new HashSet<>();
    
    @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "obraSocial" )
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();
    
    @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "obraSocial"  )
    @JsonIgnore
    private Set<Plan> planes = new HashSet<>();

    public ObraSocial(Long id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public ObraSocial() {
    }
}
