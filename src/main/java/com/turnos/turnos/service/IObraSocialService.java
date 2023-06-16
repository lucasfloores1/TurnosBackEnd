package com.turnos.turnos.service;

import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.model.Plan;
import java.util.List;
import java.util.Set;
import org.springframework.http.ResponseEntity;


public interface IObraSocialService {
    
    public List<ObraSocial> getObraSocials();
    
    public ObraSocial getObraSocialById(Long id);
    
    public ObraSocial createObraSocial(ObraSocial obraSocial);
    
    //public ObraSocial addPlanes(ObraSocial obraSocial, Set<Plan> planes);
    
    public void deleteObraSocial(Long id);
    
    public ResponseEntity<ObraSocial> updateObraSocial( Long id, ObraSocial obraSocial );
    
    public List<ObraSocial> getObrasSocialesByUser ( Long id );
    
}
