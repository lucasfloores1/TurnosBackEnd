package com.turnos.turnos.controller;

import com.turnos.turnos.model.User;
import com.turnos.turnos.service.impl.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( origins = "http://localhost:4200/" )
public class UserController {
    
    @Autowired
    private UserServiceImpl userService;
    
    @GetMapping ( "/user/load" )
    @ResponseBody
    public List<User> getUsers(){
        return userService.getUsers();
    }
    
    @DeleteMapping( "/user/delete/{id}" )
    public void deleteUser( @PathVariable Long id ){
        userService.deleteUser(id);
    }
    
    @PutMapping( "/user/update/{id}" )
    public ResponseEntity<User> updateUser( @PathVariable Long id, @RequestBody User toUpdateUser ){
        
        return userService.updateUser(id, toUpdateUser);
    
    };
    
    @PostMapping( "/user/create" )
    @ResponseBody
    public ResponseEntity<User> createUser( @RequestBody User user ) {
        ResponseEntity<User> response;
        
        User createdUser = userService.createUser(user).getBody();
        
        if ( createdUser != null ){
            
        response = ResponseEntity.ok(createdUser);
            
        }else {
            
            response = ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
            
        }
        return response;
    }  
    
}
