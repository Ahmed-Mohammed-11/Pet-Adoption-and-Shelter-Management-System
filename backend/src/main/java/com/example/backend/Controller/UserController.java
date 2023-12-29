package com.example.backend.Controller;

import com.example.backend.DTO.Request.UserDTO;
import com.example.backend.DTO.Response.UserResponseDTO;
import com.example.backend.Service.UserService;
import com.example.backend.constants.Endpoints;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoints.USER)
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }
}
