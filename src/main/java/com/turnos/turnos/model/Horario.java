package com.turnos.turnos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Horario {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;
    
    @Column ( name = "dia", nullable = false )
    private int dia;
    
    @JsonFormat(pattern = "HH:mm:ss")
    @Column ( name = "inicio", nullable = false )
    private String inicio;
    
    @JsonFormat(pattern = "HH:mm:ss")
    @Column ( name = "fin", nullable = false )
    private String fin;
    
    @Column( name = "intervalo", nullable = false )
    private int intervalo;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    private Medico_Instituto medicoInstituto;

    public Horario() {
    }

    public Horario(Long id, int dia, String inicio, String fin, int intervalo, Medico_Instituto medicoInstituto) {
        this.id = id;
        this.dia = dia;
        this.inicio = inicio;
        this.fin = fin;
        this.intervalo = intervalo;
        this.medicoInstituto = medicoInstituto;
    }
    
    
    
}
