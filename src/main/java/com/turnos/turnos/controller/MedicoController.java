package com.turnos.turnos.controller;

import com.turnos.turnos.DTO.GetMedicoDTO;
import com.turnos.turnos.DTO.HorarioDTO;
import com.turnos.turnos.DTO.InstitutoDTO;
import com.turnos.turnos.DTO.NuevoMedicoDTO;
import com.turnos.turnos.model.Horario;
import com.turnos.turnos.model.Instituto;
import com.turnos.turnos.model.Medico;
import com.turnos.turnos.model.Medico_Instituto;
import com.turnos.turnos.service.impl.HorarioServiceImpl;
import com.turnos.turnos.service.impl.InstitutoServiceImpl;
import com.turnos.turnos.service.impl.MedicoServiceImpl;
import com.turnos.turnos.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( origins = "http://localhost:4200/" )
//@CrossOrigin( origins = "https://turnos.up.railway.app/" )
public class MedicoController {
    
    @Autowired
    MedicoServiceImpl medicoService;
    @Autowired
    InstitutoServiceImpl institutoService;
    @Autowired
    HorarioServiceImpl horarioService;
    @Autowired
    UserServiceImpl userService;
    
    private static final Logger logger = LoggerFactory.getLogger(MedicoController.class);
    
    @GetMapping( "/medico/load" )
    @ResponseBody
    public List<Medico> getMedicos(){
        return medicoService.getMedicos();
    }
    
    @GetMapping( "/medico/user/{id}" )
    @ResponseBody
    public List<Medico> getMedicosByUser( @PathVariable Long id ) {
        
        return medicoService.getMedicosByUser(id);
        
    }
    
    @DeleteMapping ( "/medico/delete/{id}" )
    public  void deleteMedico ( @PathVariable Long id ){
        medicoService.deleteMedico(id);
    }
      
    @GetMapping("/medico/load/{id}")
    @ResponseBody
    public ResponseEntity<GetMedicoDTO> getMedicoById(@PathVariable Long id) {
        GetMedicoDTO medicodto = medicoService.getMedicoDTOById(id);
        return ResponseEntity.ok(medicodto);
    }

    @PostMapping("/medico/create")
    @ResponseBody
    public ResponseEntity<?> createOrUpdateMedico(@RequestBody NuevoMedicoDTO medicodto) {
        try {
            // Obtener el Medico existente por ID, si existe
            Medico medicoExistente = medicoService.getMedicoById(medicodto.getId());

            if (medicoExistente == null) {
                // No existe un Medico con el ID proporcionado, crear uno nuevo
                logger.info("no se encontro un medico, se crea uno");

                // Establecer el Instituto
                Instituto instituto = institutoService.getInstitutoById(medicodto.getIdInstituto());

                // Crear el Medico nuevo
                Medico nuevoMedico = new Medico();
                nuevoMedico.setUser(userService.getUserById(medicodto.getUserId()));
                nuevoMedico.setNombre(medicodto.getNombre());
                nuevoMedico.setTel(medicodto.getTel());
                nuevoMedico.setDni(medicodto.getDni());
                nuevoMedico.setMail(medicodto.getMail());
                nuevoMedico.setDireccion(medicodto.getDireccion());
                nuevoMedico.setMatricula(medicodto.getMatricula());

                // Guardar el Medico nuevo
                Medico savedMedico = medicoService.addMedico(nuevoMedico);
                logger.info("se creo este medico con id "+savedMedico.getId());

                // Crear una nueva instancia de Medico_Instituto
                Medico_Instituto nuevaInstancia = new Medico_Instituto();
                nuevaInstancia.setMedico(savedMedico);
                nuevaInstancia.setInstituto(instituto);

                // Guardar la nueva instancia de Medico_Instituto
                Medico_Instituto savedInstancia = medicoService.addMedico_Instituto(nuevaInstancia);
                logger.info("se creo esta instancia con id "+savedInstancia.getId());

                // Establecer la relación en el objeto Medico
                Set<Medico_Instituto> medicoInstitutoSet = new HashSet<>();
                medicoInstitutoSet.add(savedInstancia);
                savedMedico.setMedicoInstituto(medicoInstitutoSet);

                // Crear los horarios
                Set<Horario> horariosCreados = new HashSet<>();
                //Recorro el DTO
                for (Horario horarioDTO : medicodto.getHorarios()) {
                    Horario horario = new Horario();
                    horario.setId(horarioDTO.getId());
                    horario.setDia(horarioDTO.getDia());
                    horario.setInicio(horarioDTO.getInicio());
                    horario.setFin(horarioDTO.getFin());
                    horario.setIntervalo(horarioDTO.getIntervalo());
                    horario.setMedicoInstituto(savedInstancia);
                    logger.info("creo horario");
                    Horario horarioCreado = horarioService.updateOrCreateHorario(horario);
                    horariosCreados.add(horarioCreado);
                }

                // Asignar los horarios creados a la instancia de Medico_Instituto
                savedInstancia.setHorarios(horariosCreados);

                // Guardar los cambios
                medicoService.createMedico(savedMedico, savedInstancia);

                return ResponseEntity.ok(savedMedico);
            } else {
                // Ya existe un Medico con el ID proporcionado, actualizarlo
                logger.info("se encontro un medico, se actualiza");
                // Buscar una instancia existente de Medico_Instituto con el ID del Medico y del Instituto
                Medico_Instituto instanciaExistente = medicoService.getMedicoInstitutoByIds(medicoExistente.getId(), medicodto.getIdInstituto());

                if (instanciaExistente != null) {
                    // Ya existe una instancia de Medico_Instituto, actualizar los horarios
                    logger.info("se encontro una relacion existente", instanciaExistente);
                    
                    //Borro los horarios existentes
                    logger.info("borro los horarios");
                    List<Horario> toDeleteHorarios = horarioService.getHorariosByMedicoInstituto(instanciaExistente.getId());
                    for ( Horario horario : toDeleteHorarios ){
                        instanciaExistente.getHorarios().remove(horario);
                        horarioService.deleteHorario(horario.getId());
                    }
                    //Actualizo los horarios
                    for ( Horario horario : medicodto.getHorarios() ){
                        logger.info("creo horario");
                        horario.setMedicoInstituto(instanciaExistente);
                        horarioService.updateOrCreateHorario(horario);
                    }
                    
                } else {
                    // No existe una instancia de Medico_Instituto, agregar una nueva instancia con los valores proporcionados
                    logger.info("no se encontro una relacion existente se agrega una");
                    // Obtener el Instituto
                    Instituto instituto = institutoService.getInstitutoById(medicodto.getIdInstituto());
                    logger.info("se va a agregar este instituto", instituto);
                    // Crear una nueva instancia de Medico_Instituto
                    Medico_Instituto nuevaInstancia = new Medico_Instituto();
                    nuevaInstancia.setMedico(medicoExistente);
                    nuevaInstancia.setInstituto(instituto);

                    // Guardar la nueva instancia de Medico_Instituto
                    Medico_Instituto savedInstancia = medicoService.addMedico_Instituto(nuevaInstancia);
                    logger.info("se guardo la nueva instancia", savedInstancia);
                    // Crear los horarios
                    Set<Horario> horariosCreados = new HashSet<>();
                    //Recorro el DTO
                    for (Horario horarioDTO : medicodto.getHorarios()) {
                        Horario horario = new Horario();
                        horario.setId(horarioDTO.getId());
                        horario.setDia(horarioDTO.getDia());
                        horario.setInicio(horarioDTO.getInicio());
                        horario.setFin(horarioDTO.getFin());
                        horario.setIntervalo(horarioDTO.getIntervalo());
                        horario.setMedicoInstituto(savedInstancia);
                        logger.info("creo horario");
                        Horario horarioCreado = horarioService.updateOrCreateHorario(horario);
                        horariosCreados.add(horarioCreado);
                    }
                    logger.info("se crearon estos horarios", horariosCreados);
                    // Asignar los horarios creados a la instancia de Medico_Instituto
                    savedInstancia.setHorarios(horariosCreados);
                    logger.info("se guardaron los horarios en la instancia");
                    // Agregar la nueva instancia a la lista de instancias en Medico
                    medicoExistente.getMedicoInstituto().add(savedInstancia);
                    logger.info("se agrega la instancia al medico", savedInstancia);
                    // Guardar los cambios
                    medicoService.addMedico(medicoExistente);
                    logger.info("se agrego correctamente");
                }

                return ResponseEntity.ok(medicoExistente);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    }


    
}
