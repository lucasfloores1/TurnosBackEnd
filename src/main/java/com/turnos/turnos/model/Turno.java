package com.turnos.turnos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Turno {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id ;
    
    @Column( name = "fecha" )
    @JsonFormat ( pattern = "yyyy-MM-dd" )
    private LocalDateTime fecha;
    
    @Column( name = "confirmado" )
    private boolean confirmado;
    
    @Column( name = "cargado" )
    private boolean cargado;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "paciente_id" )
    private Paciente paciente;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "obraSocial_id" )
    private ObraSocial obraSocial;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "plan_id" )
    private Plan plan;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "medico_id" )
    private Medico medico;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "instituto_id" )
    private Instituto instituto;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "estudio_id" )
    private Estudio estudio;

    public Turno() {
    }

    public Turno(Long id, LocalDateTime fecha, LocalTime hora, boolean confirmado, boolean cargado, Paciente paciente, ObraSocial obraSocial, Plan plan, Medico medico, Instituto instituto, Estudio estudio) {
        this.id = id;
        this.fecha = fecha;
        this.confirmado = confirmado;
        this.cargado = cargado;
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.plan = plan;
        this.medico = medico;
        this.instituto = instituto;
        this.estudio = estudio;
    }
    
}
