package com.example.backend.Controller;


import com.example.backend.DTO.Request.AuthenticationRequestDTO;
import com.example.backend.Service.Authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<String> login(HttpServletResponse httpServletResponse, @RequestBody AuthenticationRequestDTO authenticationRequest) {

        authenticationService.authenticate(httpServletResponse, authenticationRequest.getUserName(), authenticationRequest.getPassword());

        return ResponseEntity.ok("Login successful");
    }
}
