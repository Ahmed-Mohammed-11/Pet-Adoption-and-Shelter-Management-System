package com.example.backend.DTO.Request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StaffDTO extends UserDTO {
    @NotBlank(message = "Shelter name is mandatory")
    private String shelterName;
    @NotBlank(message = "Staff role is mandatory")
    private String staffRole;
}
