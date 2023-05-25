package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Horario;
import com.turnos.turnos.model.Medico_Instituto;
import com.turnos.turnos.repository.HorarioRepository;
import com.turnos.turnos.repository.Medico_InstitutoRepository;
import com.turnos.turnos.service.IHorarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorarioServiceImpl implements IHorarioService{

    @Autowired
    HorarioRepository horarioRepository;
    @Autowired
    Medico_InstitutoRepository medicoInstitutoRepository;
    
    @Override
    public List<Horario> getHorarios() {
        return horarioRepository.findAll();
    }

    @Override
    public Horario getHorarioById(Long id) {
        return horarioRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteHorario(Long id) {
        horarioRepository.deleteById(id);
    }

    @Override
    public void createHorario(Horario horario) {
        horarioRepository.save(horario);
    }

    @Override
    public List<Horario> getHorariosByMedicoInstituto(Long id) {
        Medico_Instituto medicoInstituto = medicoInstitutoRepository.findById(id).orElse(null);
        return horarioRepository.findByMedicoInstituto(medicoInstituto);
    }
    
}
