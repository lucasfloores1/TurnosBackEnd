package com.turnos.turnos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter


public class User {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "paciente" )
    @JsonIgnore
    private List<Paciente> pacientes;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "medico" )
    @JsonIgnore
    private List<Medico> medicos;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "instituto" )
    @JsonIgnore
    private List<Instituto>institutos;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "obraSocial" )
    @JsonIgnore
    private List<ObraSocial> obraSociales;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "plan" )
    @JsonIgnore
    private List<Plan> planes;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "estudio" )
    @JsonIgnore
    private List<Estudio> estudios;

    public User() {
    }

    public User(Long id, String username, String password, List<Paciente> pacientes, List<Medico> medicos, List<Instituto> institutos, List<ObraSocial> obraSociales, List<Plan> planes, List<Estudio> estudios) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.institutos = institutos;
        this.obraSociales = obraSociales;
        this.planes = planes;
        this.estudios = estudios;
    }
    
    
    
}
