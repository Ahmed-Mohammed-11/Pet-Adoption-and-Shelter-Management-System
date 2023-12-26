package com.example.backend.Controller;

import com.example.backend.DTO.Request.RegistrationDTO;
import com.example.backend.Service.Registeration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationDTO registrationRequestDTO){
        registrationService.register(registrationRequestDTO);
        return ResponseEntity.ok("User registered successfully");
    }
}
