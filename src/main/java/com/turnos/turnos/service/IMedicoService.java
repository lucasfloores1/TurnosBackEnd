package com.turnos.turnos.service;

import com.turnos.turnos.DTO.GetMedicoDTO;
import com.turnos.turnos.model.Medico;
import com.turnos.turnos.model.Medico_Instituto;
import java.util.List;


public interface IMedicoService {
    
    public List<Medico> getMedicos();
    
    public List<Medico> getMedicosByUser( Long id );
    
    public Medico getMedicoById(Long id);
    
    public void deleteMedico(Long id);
    
    public void createMedico( Medico medico, Medico_Instituto medico_Instituto);
    
    public Medico addMedico(Medico medico);
    
    public Medico_Instituto addMedico_Instituto(Medico_Instituto medico_instituto);
    
    public void updateMedicoAndMedicoInstituto(Medico medico, Medico_Instituto medicoInstituto);
    
    public Medico_Instituto getMedicoInstitutoByIds( Long medicoId, Long institutoId );
    
    public GetMedicoDTO getMedicoDTOById(Long id);
    
}
