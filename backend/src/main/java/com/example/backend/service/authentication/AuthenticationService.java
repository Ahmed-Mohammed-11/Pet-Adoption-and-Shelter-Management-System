package com.example.backend.service.authentication;

import com.example.backend.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private AuthenticationManager authenticationManager;
    private JDBCUserDetailsService userDetailsService;
    private JWTUtil jwtUtil;

    public String authenticate(String userName, String password) throws BadCredentialsException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password));
        return jwtUtil.generateToken(userDetailsService.loadUserByUsername(userName));
    }
}