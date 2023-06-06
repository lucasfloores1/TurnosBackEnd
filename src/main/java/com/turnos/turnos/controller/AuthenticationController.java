package com.turnos.turnos.controller;

import com.turnos.turnos.security.JwtRequest;
import com.turnos.turnos.security.JwtResponse;
import com.turnos.turnos.security.JwtUtils;
import com.turnos.turnos.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @PostMapping("/user/generate-token")
    public ResponseEntity<?> generateToken( @RequestBody JwtRequest jwtRequest ) throws Exception{
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception( "Usuario no encontrado" );
        }
        
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    private void authenticate( String username, String password ) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException disabledException) {
            throw new Exception ( "Usuario Deshabilitado" + disabledException.getMessage() );
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception ( "Credenciales invalidas" + badCredentialsException.getMessage() );
        }
    }

    
}
