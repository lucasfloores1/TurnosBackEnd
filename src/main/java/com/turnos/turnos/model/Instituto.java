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
public class Instituto {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id ;
    
    @Column ( name = "nombre", length = 50, nullable = false )
    private String nombre;
    
    @Column ( name = "direccion", length = 100, nullable = false )
    private String direccion;

    @Column ( name = "cuit", length = 50 )
    private String cuit;    
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "instituto" )
    @JsonIgnore
    private Set<Medico_Instituto> medicoInstituto = new HashSet<>();
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "instituto" )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();
    
    @ManyToOne
    @JsonIgnore
    private User user;

    public Instituto() {
    }

    public Instituto(Long id, String nombre, String direccion, String cuit, User user) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuit = cuit;
        this.user = user;
    }

}
