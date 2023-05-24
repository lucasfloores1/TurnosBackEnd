package com.turnos.turnos.service;

import com.turnos.turnos.model.ObraSocial;
import java.util.List;
import org.springframework.http.ResponseEntity;


public interface IObraSocialService {
    
    public List<ObraSocial> getObraSocials();
    
    public ObraSocial getObraSocialById(Long id);
    
    public ResponseEntity<ObraSocial> createObraSocial(ObraSocial obraSocial);
    
    public void deleteObraSocial(Long id);
    
    public ResponseEntity<ObraSocial> updateObraSocial( Long id, ObraSocial obraSocial );
    
}
