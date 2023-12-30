package com.example.backend.controller;


import com.example.backend.dto.Request.AuthenticationRequestDTO;
import com.example.backend.service.authentication.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequestDTO authenticationRequest) {

        String token = authenticationService.authenticate(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
        );
        return ResponseEntity.ok(token);
    }
}
