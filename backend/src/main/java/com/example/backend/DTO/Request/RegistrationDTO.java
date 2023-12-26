package com.example.backend.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationDTO {
    private String userName;
    private String password;
    private String email;
}
