package com.turnos.turnos.service;

import com.turnos.turnos.model.Estudio;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface IEstudioService {
    
    public List<Estudio> getEstudiosByUser( Long id );
    
    public List<Estudio> getEstudios();
    
    public Estudio getEstudioById(Long id);
    
    public void deleteEstudio(Long id);
    
    public ResponseEntity<Estudio> createEstudio(Estudio estudio);
    
    public ResponseEntity<Estudio> updateEstudio(Long id, Estudio toUpdateEstudio);
    
}
