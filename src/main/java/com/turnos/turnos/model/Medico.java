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
public class Medico extends Persona{
    
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id ;
    
    @Column ( name = "matricula", nullable = false )
    private int matricula;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "medico" )
    private Set<Medico_Instituto> medicoInstituto = new HashSet<>();
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "medico" )
    private Set<Turno> turnos = new HashSet<>();

    public Medico() {
    }

    public Medico(Long id, int matricula) {
        this.id = id;
        this.matricula = matricula;
    }

    public Medico(Long id, int matricula, String nombre, Long dni, String tel, String mail, String direccion) {
        super(nombre, dni, tel, mail, direccion);
        this.id = id;
        this.matricula = matricula;
    }
    
}
