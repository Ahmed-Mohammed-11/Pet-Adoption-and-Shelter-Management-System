package com.example.backend.controller;

import com.example.backend.dto.Response.UserResponseDTO;
import com.example.backend.service.UserService;
import com.example.backend.constants.Endpoints;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoints.USER)
public class UserController {

    private UserService userService;

//    @GetMapping
//    public ResponseEntity<UserResponseDTO> getUser(@RequestParam String username) {
//        return ResponseEntity.ok(userService.getUser(username));
//    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUserProfile(@AuthenticationPrincipal String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }
}
