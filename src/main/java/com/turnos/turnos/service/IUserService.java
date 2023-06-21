package com.turnos.turnos.service;

import com.turnos.turnos.model.User;
import java.util.List;
import org.springframework.http.ResponseEntity;


public interface IUserService {
    
    public List<User> getUsers();
    
    public User getUserById(Long id);
    
    public User createUser(User user);
    
    public void deleteUser(Long id);
    
    public ResponseEntity<User> updateUser( Long id, User user );
    
}
