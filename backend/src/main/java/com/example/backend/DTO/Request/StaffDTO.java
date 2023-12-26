package com.example.backend.DTO.Request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;


public class StaffDTO extends UserDTO {
    @NotBlank(message = "Shelter name is mandatory")
    private String shelterName;
    @NotBlank(message = "Staff role is mandatory")
    private String staffRole;
}
