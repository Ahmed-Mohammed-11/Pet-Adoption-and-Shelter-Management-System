package com.example.backend.controller;


import com.example.backend.dto.Request.AuthenticationRequestDTO;
import com.example.backend.dto.Request.UserDTO;
import com.example.backend.dto.Response.LoginResponseDTO;
import com.example.backend.dto.Response.UserResponseDTO;
import com.example.backend.service.UserService;
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
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationRequestDTO authenticationRequest) {

        String token = authenticationService.authenticate(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
        );
        UserResponseDTO userDTO = userService.getUser(authenticationRequest.getUsername());
        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
                .token(token)
                .role(String.valueOf(userDTO.getRole()))
                .build();

        return ResponseEntity.ok(loginResponseDTO);
    }
}
