package com.turnos.turnos.service.impl;

import com.turnos.turnos.DTO.NuevoPacienteDTO;
import com.turnos.turnos.DTO.PacienteOSDTO;
import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.model.Paciente;
import com.turnos.turnos.model.Paciente_ObraSocial;
import com.turnos.turnos.model.User;
import com.turnos.turnos.repository.ObraSocialRepository;
import com.turnos.turnos.repository.PacienteRepository;
import com.turnos.turnos.repository.Paciente_ObraSocialRepository;
import com.turnos.turnos.repository.PlanRepository;
import com.turnos.turnos.repository.UserRepository;
import com.turnos.turnos.service.IPacienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    public PacienteRepository pacienteRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public ObraSocialRepository obraSocialRepository;
    @Autowired
    public PlanRepository planRepository;
    
    @Autowired
    public Paciente_ObraSocialRepository paciente_obraSocialRepository;
    
    @Override
    public List<Paciente> getPacientes() {
        
        return pacienteRepository.findAll();
        
    }

    @Override
    public void createPaciente(Paciente paciente, Paciente_ObraSocial paciente_obraSocial) {
        
        pacienteRepository.save( paciente );
        paciente_obraSocialRepository.save( paciente_obraSocial );
        
    }

    @Override
    public void deletePaciente(Long id) {
        
        pacienteRepository.deleteById(id);
        
    }

    @Override
    public ResponseEntity<Paciente> updatePaciente(Long id, Paciente toUpdatePaciente) {
        
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        
        paciente.setNombre ( toUpdatePaciente.getNombre() );
        paciente.setTel ( toUpdatePaciente.getTel() );
        paciente.setDireccion ( toUpdatePaciente.getDireccion() );
        paciente.setMail ( toUpdatePaciente.getMail() );
        
        Paciente updatedPaciente = pacienteRepository.save(paciente);
        
        return ResponseEntity.ok(updatedPaciente);
        
    }

    @Override
    public Paciente getPacienteById(Long id) {
        
        return pacienteRepository.findById(id).orElse(null);
        
    }

    @Override
    public List<Paciente> getPacientesByUser(Long id) {
        
        User user = userRepository.findById(id).orElse(null);
        return pacienteRepository.findByUser(user);
        
    }

    @Override
    public Paciente createOrUpdatePaciente(NuevoPacienteDTO pacienteDTO) {
        // Verifico si el paciente existe
        Paciente paciente = pacienteRepository.findById(pacienteDTO.getId()).orElse(new Paciente());
        boolean isNewPaciente = false;
        if (paciente.getId() == null) { isNewPaciente = true; }
        // Actualizo los datos del paciente
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setMail(pacienteDTO.getMail());
        paciente.setDni(pacienteDTO.getDni());
        paciente.setTel(pacienteDTO.getTel());
        paciente.setDireccion(pacienteDTO.getDireccion());
        paciente.setUser(userRepository.findById(pacienteDTO.getUserId()).orElse(null));
        Paciente savedPaciente = pacienteRepository.save(paciente);

        // Verifico si el paciente es nuevo y asocio la obra social "Particular"
        if (isNewPaciente) {
            ObraSocial particular = obraSocialRepository.findByNombreAndUser("Particular", paciente.getUser());

            if (particular != null) {
                Paciente_ObraSocial pacienteObraSocialParticular = new Paciente_ObraSocial();
                pacienteObraSocialParticular.setPaciente(savedPaciente);
                pacienteObraSocialParticular.setObraSocial(particular);
                pacienteObraSocialParticular.setPlan(planRepository.findByNombreAndObraSocial("Particular", particular));
                pacienteObraSocialParticular.setAfiliado(pacienteDTO.getDni().toString());

                Paciente_ObraSocial savedParticular = paciente_obraSocialRepository.save(pacienteObraSocialParticular);

                savedPaciente.getObrasSociales().add(savedParticular);
            }
        }
        // Verifico las obras sociales adicionales recibidas en el DTO
        if (pacienteDTO.getObrasSociales() != null) {
            for (PacienteOSDTO pacienteOSDTO : pacienteDTO.getObrasSociales()) {
                ObraSocial obraSocial = obraSocialRepository.findById(pacienteOSDTO.getIdObraSocial()).orElse(null);
                if (obraSocial != null) {
                    Optional<Paciente_ObraSocial> pacienteObraSocialOptional = paciente_obraSocialRepository
                            .findByPacienteAndObraSocial(savedPaciente, obraSocial);
                    Paciente_ObraSocial pacienteObraSocial;
                    if (pacienteObraSocialOptional.isPresent()) {
                        pacienteObraSocial = pacienteObraSocialOptional.get();
                    } else {
                        pacienteObraSocial = new Paciente_ObraSocial();
                        pacienteObraSocial.setPaciente(savedPaciente);
                        pacienteObraSocial.setObraSocial(obraSocial);
                    }

                    pacienteObraSocial.setPlan(planRepository.findById(pacienteOSDTO.getIdPlan()).orElse(null));
                    pacienteObraSocial.setAfiliado(pacienteOSDTO.getAfiliado());

                    // Guardo la entidad pacienteObraSocial antes de asociarla al paciente
                    paciente_obraSocialRepository.save(pacienteObraSocial);

                    savedPaciente.getObrasSociales().add(pacienteObraSocial);
                }
            }
        }

        // Guardo el paciente y las entidades relacionadas
        return pacienteRepository.save(savedPaciente);
    }

    
    
    
}
