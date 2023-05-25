package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.repository.ObraSocialRepository;
import com.turnos.turnos.service.IObraSocialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ObraSocialServiceImpl implements IObraSocialService {

    @Autowired
    public ObraSocialRepository obraSocialRepository;
    
    @Override
    public List<ObraSocial> getObraSocials() {
        
        return obraSocialRepository.findAll();
        
    }

    /*@Override
    public ObraSocial createObraSocial(ObraSocial obraSocial) {
        
        ObraSocial createdObraSocial = obraSocialRepository.findById(obraSocial.getId()).orElse(null);
        
        createdObraSocial.setNombre(obraSocial.getNombre());
        createdObraSocial.setDireccion(obraSocial.getDireccion());
        createdObraSocial.setPlanes(obraSocial.getPlanes());
        
        return obraSocialRepository.save(createdObraSocial);
        
    }*/
    
    @Override
    public ObraSocial createObraSocial(ObraSocial obraSocial) {
        ObraSocial createdObraSocial = new ObraSocial();
        createdObraSocial.setNombre(obraSocial.getNombre());
        createdObraSocial.setDireccion(obraSocial.getDireccion());
        createdObraSocial.setPlanes(obraSocial.getPlanes());
        return obraSocialRepository.save(createdObraSocial);
    }
    

    @Override
    public void deleteObraSocial(Long id) {
        
        obraSocialRepository.deleteById(id);
        
    }

    @Override
    public ResponseEntity<ObraSocial> updateObraSocial(Long id, ObraSocial toUpdateObraSocial) {
        
        ObraSocial obraSocial = obraSocialRepository.findById(id).orElse(null);
        
        obraSocial.setNombre ( toUpdateObraSocial.getNombre() );
        obraSocial.setDireccion ( toUpdateObraSocial.getDireccion() );
        
        ObraSocial updatedObraSocial = obraSocialRepository.save(obraSocial);
        
        return ResponseEntity.ok(updatedObraSocial);
        
    }

    @Override
    public ObraSocial getObraSocialById(Long id) {
        return obraSocialRepository.findById(id).orElse(null);
    }
    
}
