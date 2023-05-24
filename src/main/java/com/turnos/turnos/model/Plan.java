package com.turnos.turnos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Plan {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    
    @Column ( name = "nombre", length = 50, nullable = false )
    private String nombre;
    
    @ManyToOne
    private ObraSocial obraSocial;
    
    @OneToMany(mappedBy = "plan")
    private Set<Turno> turnos;
    
    @OneToMany( mappedBy = "plan" )
    private Set<Paciente_ObraSocial> pacientes;

    public Plan() {
    }

    public Plan(Long id, String nombre, ObraSocial obraSocial, Set<Turno> turnos, Set<Paciente_ObraSocial> pacientes) {
        this.id = id;
        this.nombre = nombre;
        this.obraSocial = obraSocial;
        this.turnos = turnos;
        this.pacientes = pacientes;
    }


    
}
