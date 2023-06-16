package com.turnos.turnos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Paciente extends Persona {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id ;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "paciente" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Paciente_ObraSocial> obrasSociales = new HashSet<>();
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "paciente" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();
    
    @ManyToOne
    @JsonIgnore
    private User user;

    public Paciente(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Paciente(Long id, User user, String nombre, Long dni, String tel, String mail, String direccion) {
        super(nombre, dni, tel, mail, direccion);
        this.id = id;
        this.user = user;
    }

    public Paciente() {
    }
 
}
