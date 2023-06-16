package com.turnos.turnos.service;

import com.turnos.turnos.model.Instituto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface IInstitutoService {
    
    public List<Instituto> getInstitutos();
    
    public Instituto getInstitutoById(Long id);
    
    public void deleteInstituto(Long id);
    
    public ResponseEntity<Instituto> createInstituto(Instituto instituto);
    
    public ResponseEntity<Instituto> updateInstituto( Long id, Instituto instituto );
    
    public List<Instituto> getInstitutosByUser(Long id);
    
}
