package com.turnos.turnos.service;

import com.turnos.turnos.model.Horario;
import java.util.List;

public interface IHorarioService {
    
    public List<Horario> getHorarios();
    
    public Horario getHorarioById(Long id);
    
    public void deleteHorario(Long id);
    
    public void createHorario(Horario horario);
    
}
