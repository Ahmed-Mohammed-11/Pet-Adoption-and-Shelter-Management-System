package com.example.backend.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequestDTO{
    private String userName;
    private String password;
}