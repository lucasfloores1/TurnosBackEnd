package com.turnos.turnos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Paciente_ObraSocial {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id ;
    
    @Column ( name = "afiliado", length = 30, nullable = false )
    private String afiliado;
    
    @ManyToOne( fetch = FetchType.EAGER )
    private Paciente paciente;
    
    @ManyToOne( fetch = FetchType.EAGER )
    private ObraSocial obraSocial;
    
    @ManyToOne( fetch = FetchType.EAGER )
    private Plan plan;

    public Paciente_ObraSocial() {
    }

    public Paciente_ObraSocial(Long id, String afiliado, Paciente paciente, ObraSocial obraSocial, Plan plan) {
        this.id = id;
        this.afiliado = afiliado;
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.plan = plan;
    }
    
}
