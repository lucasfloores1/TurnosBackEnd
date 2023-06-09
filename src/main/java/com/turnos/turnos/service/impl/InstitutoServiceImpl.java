package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Instituto;
import com.turnos.turnos.model.User;
import com.turnos.turnos.repository.InstitutoRepository;
import com.turnos.turnos.repository.UserRepository;
import com.turnos.turnos.service.IInstitutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InstitutoServiceImpl implements IInstitutoService {

    @Autowired
    InstitutoRepository institutoRepository;
    @Autowired
    UserRepository userRepository;
    
    @Override
    public List<Instituto> getInstitutos() {
        return institutoRepository.findAll();
    }

    @Override
    public Instituto getInstitutoById(Long id) {
        return institutoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteInstituto(Long id) {
        institutoRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Instituto> createInstituto(Instituto instituto) {
        
        Instituto createdInstituto = institutoRepository.save(instituto);
        
        return ResponseEntity.ok(createdInstituto);
        
    }
    
    @Override
    public ResponseEntity<Instituto> updateInstituto(Long id, Instituto toUpdateInstituto) {
        
        Instituto instituto = institutoRepository.findById(id).orElse(null);
        
        instituto.setNombre ( toUpdateInstituto.getNombre() );
        instituto.setDireccion ( toUpdateInstituto.getDireccion() );
        instituto.setCuit(toUpdateInstituto.getCuit());
        
        Instituto updatedInstituto = institutoRepository.save(instituto);
        
        return ResponseEntity.ok(updatedInstituto);
        
    }

    @Override
    public List<Instituto> getInstitutosByUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return institutoRepository.findByUser(user);
    }
    
}
