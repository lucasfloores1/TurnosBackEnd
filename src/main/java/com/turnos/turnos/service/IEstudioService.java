package com.turnos.turnos.service;

import com.turnos.turnos.model.Estudio;
import java.util.List;

public interface IEstudioService {
    
    public List<Estudio> getEstudios();
    
    public Estudio getEstudioById(Long id);
    
    public void deleteEstudio(Long id);
    
    public void createEstudio(Estudio estudio);
    
}
