package com.example.backend.dto.Response;

import com.example.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    protected int userId;
    protected String username;
    protected String email;
    protected String phone;
    protected String firstName;
    protected String lastName;
    protected Role role;
    private int shelterId;
    private String staffRole;

}
