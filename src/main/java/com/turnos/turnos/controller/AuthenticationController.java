package com.turnos.turnos.controller;

import com.turnos.turnos.DTO.AuthLoginDTO;
import com.turnos.turnos.model.User;
import com.turnos.turnos.security.JwtRequest;
import com.turnos.turnos.security.JwtResponse;
import com.turnos.turnos.security.JwtUtils;
import com.turnos.turnos.service.impl.UserDetailsServiceImpl;
import com.turnos.turnos.service.impl.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin( origins = "http://localhost:4200/" )
@CrossOrigin( origins = "https://turnomed.up.railway.app/" )
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private UserServiceImpl userService;
    
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
    
    @GetMapping( "/user/actual-user" )
    public User getActualUser( Principal principal ){
        return (User) this.userDetailsService.loadUserByUsername(principal.getName());
    }
    
    @GetMapping("/user/validate/{token}/{id}")
    @ResponseBody
    public ResponseEntity<?> validateUser(@PathVariable String token, @PathVariable Long id) {
        User user = userService.getUserById(id);
        AuthLoginDTO response = new AuthLoginDTO();
        response.setToken(token);
        response.setUser(user);

        try {
            if (jwtUtils.validateToken(token, user) && user.isEnable()) {
                response.setValid(true);
                return ResponseEntity.ok(response);
            } else {
                response.setValid(false);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expired or Account not verified");
            }
        } catch (ExpiredJwtException ex) {
            response.setValid(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expired");
        }
    }
}
