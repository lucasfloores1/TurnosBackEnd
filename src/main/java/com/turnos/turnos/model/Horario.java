package com.turnos.turnos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalTime;
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
    private LocalTime inicio;
    
    @JsonFormat(pattern = "HH:mm:ss")
    @Column ( name = "fin", nullable = false )
    private LocalTime fin;
    
    @ManyToOne ( fetch = FetchType.EAGER )
    private Medico_Instituto medicoInstituto;
    
}
