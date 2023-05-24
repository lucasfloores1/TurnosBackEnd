package com.turnos.turnos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Medico_Instituto {

    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id ;
    
    @Column( name = "inicio" )
    private Date inicio;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    private Medico medico;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    private Instituto instituto;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "medico_instituto" )
    private Set<Medico_Instituto_Horario> horarios = new HashSet<>();

    public Medico_Instituto() {
    }

    public Medico_Instituto(Long id, Date inicio, Medico medico, Instituto instituto) {
        this.id = id;
        this.inicio = inicio;
        this.medico = medico;
        this.instituto = instituto;
    }
    
}
