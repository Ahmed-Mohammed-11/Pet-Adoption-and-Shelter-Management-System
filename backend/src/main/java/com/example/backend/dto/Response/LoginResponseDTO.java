package com.example.backend.dto.Response;

import com.example.backend.dto.Request.UserDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponseDTO {
    String token;
    String role;
}
