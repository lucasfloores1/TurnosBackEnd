package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.User;
import com.turnos.turnos.repository.UserRepository;
import com.turnos.turnos.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;
    
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        
        User createdUser = userRepository.save(user);
        
        return ResponseEntity.ok(createdUser);
        
    }
    
    @Override
    public ResponseEntity<User> updateUser(Long id, User toUpdateUser) {
        
        User user = userRepository.findById(id).orElse(null);
        
        user.setEmail   (toUpdateUser.getEmail());
        user.setUsername ( toUpdateUser.getUsername());
        user.setPassword( toUpdateUser.getPassword());
        
        User updatedUser = userRepository.save(user);
        
        return ResponseEntity.ok(updatedUser);
        
    }
    
}
