package com.turnos.turnos.service;

import com.turnos.turnos.model.Horario;
import com.turnos.turnos.model.Medico;
import com.turnos.turnos.model.Medico_Instituto;
import java.util.HashSet;
import java.util.List;


public interface IMedicoService {
    
    public List<Medico> getMedicos();
    
    public Medico getMedicoById(Long id);
    
    public void deleteMedico(Long id);
    
    public void createMedico( Medico medico, Medico_Instituto medico_Instituto);
    
}
