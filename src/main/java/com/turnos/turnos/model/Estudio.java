package com.turnos.turnos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class Estudio {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id ;
    
    @Column ( name = "nombre", length = 100, nullable = false )
    private String nombre;
    
    @Column ( name = "nomenclador", length = 20, nullable = false )
    private String nomenclador;
    
    @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "estudio" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();
    
    @ManyToOne
    @JsonIgnore
    private User user;

    public Estudio() {
    }

    public Estudio(Long id, String nombre, String nomenclador, User user) {
        this.id = id;
        this.nombre = nombre;
        this.nomenclador = nomenclador;
        this.user = user;
    }
    
}
