package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Estudio;
import com.turnos.turnos.repository.EstudioRepository;
import com.turnos.turnos.service.IEstudioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EstudioServiceImpl implements IEstudioService{

    @Autowired
    EstudioRepository estudioRepository;
    
    @Override
    public List<Estudio> getEstudios() {
        return estudioRepository.findAll();
    }

    @Override
    public Estudio getEstudioById(Long id) {
        return estudioRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEstudio(Long id) {
        estudioRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Estudio> createEstudio(Estudio estudio) {
        
        Estudio createdEstudio = estudioRepository.save(estudio);
        
        return ResponseEntity.ok(createdEstudio);
        
    }
    
    @Override
    public ResponseEntity<Estudio> updateEstudio(Long id, Estudio toUpdateEstudio) {
        
        Estudio estudio = estudioRepository.findById(id).orElse(null);
        
        estudio.setNombre ( toUpdateEstudio.getNombre());
        estudio.setNomenclador(toUpdateEstudio.getNomenclador());
        
        Estudio updatedEstudio = estudioRepository.save(estudio);
        
        return ResponseEntity.ok(updatedEstudio);
        
    }
    
}
