
package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Medico;
import com.turnos.turnos.model.Medico_Instituto;
import com.turnos.turnos.repository.MedicoRepository;
import com.turnos.turnos.repository.Medico_InstitutoRepository;
import com.turnos.turnos.service.IMedicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoServiceImpl implements IMedicoService {
    
    @Autowired
    MedicoRepository medicoRepository;
    Medico_InstitutoRepository medico_InstitutoRepository;

    @Override
    public List<Medico> getMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public Medico getMedicoById(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMedico(Long id) {
        medicoRepository.deleteById(id);
    }

    @Override
    public void createMedico(Medico medico, Medico_Instituto medico_Instituto) {
        
        medicoRepository.save(medico);
        medico_InstitutoRepository.save(medico_Instituto);
        
    }
    
}
