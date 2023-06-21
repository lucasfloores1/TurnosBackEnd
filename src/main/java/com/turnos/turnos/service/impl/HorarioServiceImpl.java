package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Horario;
import com.turnos.turnos.model.Medico_Instituto;
import com.turnos.turnos.repository.HorarioRepository;
import com.turnos.turnos.repository.Medico_InstitutoRepository;
import com.turnos.turnos.service.IHorarioService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Horario createHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    @Override
    public List<Horario> getHorariosByMedicoInstituto(Long id) {
        Medico_Instituto medicoInstituto = medicoInstitutoRepository.findById(id).orElse(null);
        return horarioRepository.findByMedicoInstituto(medicoInstituto);
    }

    @Override
    public Horario updateOrCreateHorario(Horario horario) {
        
        //Leo el horario recibido
        Horario nuevoHorario = getHorarioById(horario.getId());
        
        if(nuevoHorario == null){
            return createHorario(horario);
        } else {
            nuevoHorario.setDia(horario.getDia());
            nuevoHorario.setInicio(horario.getInicio());
            nuevoHorario.setFin(horario.getFin());
            nuevoHorario.setIntervalo(horario.getIntervalo());
            return createHorario(nuevoHorario);
        }
    }
    
}
