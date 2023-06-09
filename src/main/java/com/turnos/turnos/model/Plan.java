package com.turnos.turnos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class Plan {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    
    @Column ( name = "nombre", length = 50, nullable = false )
    private String nombre;
    
    @ManyToOne
    private ObraSocial obraSocial;
    
    @OneToMany(mappedBy = "plan")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Turno> turnos;
    
    @OneToMany( mappedBy = "plan" )
    @JsonIgnore
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
