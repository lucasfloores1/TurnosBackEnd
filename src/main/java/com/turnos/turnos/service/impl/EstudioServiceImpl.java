package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Estudio;
import com.turnos.turnos.repository.EstudioRepository;
import com.turnos.turnos.service.IEstudioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createEstudio(Estudio estudio) {
        estudioRepository.save(estudio);
    }
    
}
