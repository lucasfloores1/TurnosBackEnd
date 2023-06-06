package com.turnos.turnos.security;

import com.turnos.turnos.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
          .csrf().disable() // (2)
          .authorizeHttpRequests((authorize) -> authorize
              .requestMatchers("/user/**").permitAll()
              .anyRequest().authenticated()
          )
          .cors().disable()
          .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
          .sessionManagement((session) -> session
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          );
      ;
      /*
      http
          .formLogin(withDefaults()); // (1)
      http
          .httpBasic(withDefaults()); // (1)
       */
      return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration
        authenticationConfiguration) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
    }
    
}