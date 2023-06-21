
package com.turnos.turnos.service.impl;

import com.turnos.turnos.DTO.GetMedicoDTO;
import com.turnos.turnos.DTO.HorarioDTO;
import com.turnos.turnos.DTO.InstitutoDTO;
import com.turnos.turnos.model.Horario;
import com.turnos.turnos.model.Instituto;
import com.turnos.turnos.model.Medico;
import com.turnos.turnos.model.Medico_Instituto;
import com.turnos.turnos.model.User;
import com.turnos.turnos.repository.MedicoRepository;
import com.turnos.turnos.repository.Medico_InstitutoRepository;
import com.turnos.turnos.repository.UserRepository;
import com.turnos.turnos.service.IMedicoService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        return medicoRepository.save(medico);
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

    @Override
    public void updateMedicoAndMedicoInstituto(Medico medico, Medico_Instituto medicoInstituto) {
            // Guardar el médico actualizado
        Medico updatedMedico = addMedico(medico);

        // Establecer la relación en el objeto Medico
        Set<Medico_Instituto> medicoInstitutoSet = new HashSet<>();
        medicoInstitutoSet.add(medicoInstituto);
        updatedMedico.setMedicoInstituto(medicoInstitutoSet);

        // Guardar la nueva instancia de Medico_Instituto
        Medico_Instituto savedMedicoInstituto = medico_InstitutoRepository.save(medicoInstituto);

        // Asignar el médico actualizado a la instancia de Medico_Instituto
        savedMedicoInstituto.setMedico(updatedMedico);

        // Guardar los cambios en el médico y el Medico_Instituto
        medicoRepository.save(updatedMedico);
        medico_InstitutoRepository.save(savedMedicoInstituto);
    }

    @Override
    public Medico_Instituto getMedicoInstitutoByIds(Long medicoId, Long institutoId) {
        return medico_InstitutoRepository.findByMedicoIdAndInstitutoId(medicoId, institutoId);
    }

    @Override
    public GetMedicoDTO getMedicoDTOById(Long id) {
        Medico medico = medicoRepository.findById(id).orElse(null);
        if (medico == null) {
            try {
                // Manejo de la situación cuando no se encuentra el médico con el ID especificado
                throw new Exception("Médico no encontrado");
            } catch (Exception ex) {
                Logger.getLogger(MedicoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        GetMedicoDTO medicodto = new GetMedicoDTO();
        medicodto.setId(medico.getId());
        medicodto.setNombre(medico.getNombre());
        medicodto.setDni(medico.getDni());
        medicodto.setMail(medico.getMail());
        medicodto.setDireccion(medico.getDireccion());
        medicodto.setTel(medico.getTel());
        medicodto.setMatricula(medico.getMatricula());

        List<InstitutoDTO> institutosDTO = new ArrayList<>();
        for (Medico_Instituto medicoInstituto : medico.getMedicoInstituto()) {
            Instituto instituto = medicoInstituto.getInstituto();
            InstitutoDTO institutoDTO = new InstitutoDTO();
            institutoDTO.setId(instituto.getId());
            institutoDTO.setNombre(instituto.getNombre());
            institutoDTO.setDireccion(instituto.getDireccion());

            List<HorarioDTO> horariosDTO = new ArrayList<>();
            for (Horario horario : medicoInstituto.getHorarios()) {
                HorarioDTO horarioDTO = new HorarioDTO();
                horarioDTO.setId(horario.getId());
                horarioDTO.setDia(horario.getDia());
                horarioDTO.setInicio(horario.getInicio());
                horarioDTO.setFin(horario.getFin());
                horarioDTO.setIntervalo(horario.getIntervalo());

                horariosDTO.add(horarioDTO);
            }
            institutoDTO.setHorarios(horariosDTO);

            institutosDTO.add(institutoDTO);
        }
        medicodto.setInstitutos(institutosDTO);

        return medicodto;
    }
    
    
}
