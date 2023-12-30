package com.example.backend.controller;

import com.example.backend.dto.Request.StaffDTO;
import com.example.backend.dto.Request.UserDTO;
import com.example.backend.service.registeration.RegistrationService;
import com.example.backend.constants.Endpoints;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @PostMapping(Endpoints.REGISTER_ADOPTER)
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO){
        registrationService.registerAdopter(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping(Endpoints.REGISTER_STAFF)
    public ResponseEntity<String> registerStaff(@RequestBody @Valid StaffDTO staffDTO){
        System.out.println("Registering staff");
        registrationService.registerStaff(staffDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping(Endpoints.REGISTER_MANAGER)
    public ResponseEntity<String> registerManager(@RequestBody @Valid UserDTO userDTO){
        registrationService.registerManager(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }

}
