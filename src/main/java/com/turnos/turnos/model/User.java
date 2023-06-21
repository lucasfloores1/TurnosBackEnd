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
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
public class User implements UserDetails{
    
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "tel", nullable = false)
    private String tel;
    
    @Column(name = "verification")
    @JsonIgnore
    private String verification;
    
    @Column(name = "enable")
    private boolean enable;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user" )
    @JsonIgnore
    private List<Paciente> pacientes;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user" )
    @JsonIgnore
    private List<Medico> medicos;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user" )
    @JsonIgnore
    private List<Instituto>institutos;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user" )
    @JsonIgnore
    private List<ObraSocial> obraSociales;
    
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user" )
    @JsonIgnore
    private List<Estudio> estudios;

    public User() {
    }

    public User(Long id, String nombre, String tel, String verification, boolean enable, String email, String username, String password, List<Paciente> pacientes, List<Medico> medicos, List<Instituto> institutos, List<ObraSocial> obraSociales, List<Estudio> estudios) {
        this.id = id;
        this.nombre = nombre;
        this.tel = tel;
        this.verification = verification;
        this.enable = enable;
        this.email = email;
        this.username = username;
        this.password = password;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.institutos = institutos;
        this.obraSociales = obraSociales;
        this.estudios = estudios;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
    
    
    
}
