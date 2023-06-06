
package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Medico;
import com.turnos.turnos.model.Medico_Instituto;
import com.turnos.turnos.model.User;
import com.turnos.turnos.repository.MedicoRepository;
import com.turnos.turnos.repository.Medico_InstitutoRepository;
import com.turnos.turnos.repository.UserRepository;
import com.turnos.turnos.service.IMedicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoServiceImpl implements IMedicoService {
    
    @Autowired
    MedicoRepository medicoRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
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
        
        medico_InstitutoRepository.save(medico_Instituto);
        medicoRepository.save(medico);
        
    }

    @Override
    public Medico addMedico(Medico medico) {
        
        Medico createdMedico = new Medico();
        createdMedico.setNombre(medico.getNombre());
        createdMedico.setDni(medico.getDni());
        createdMedico.setDireccion(medico.getDireccion());
        createdMedico.setMail(medico.getMail());
        createdMedico.setMatricula(medico.getMatricula());
        createdMedico.setTel(medico.getTel());
        createdMedico.setMedicoInstituto(medico.getMedicoInstituto());
        
        return medicoRepository.save(createdMedico);
    }

    @Override
    public Medico_Instituto addMedico_Instituto(Medico_Instituto medico_instituto) {
        return medico_InstitutoRepository.save(medico_instituto);
    }

    @Override
    public List<Medico> getMedicosByUser(Long id) {
        
        User user = userRepository.findById(id).orElse(null);
        return medicoRepository.findByUser(user);
        
    }
    
}
