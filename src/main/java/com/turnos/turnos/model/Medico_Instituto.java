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

@Entity
@Getter
@Setter
public class Medico_Instituto {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id ;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    private Medico medico;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    private Instituto instituto;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "medicoInstituto" )
    @JsonIgnore
    private Set<Horario> horarios = new HashSet<>();

    public Medico_Instituto() {
    }

    public Medico_Instituto(Long id, Medico medico, Instituto instituto) {
        this.id = id;
        this.medico = medico;
        this.instituto = instituto;
    }
    
}
