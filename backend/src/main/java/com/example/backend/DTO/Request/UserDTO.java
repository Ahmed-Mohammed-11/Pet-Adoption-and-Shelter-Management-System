package com.example.backend.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.backend.constants.RegularExpressions.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = EMAIL_REGEX, message = "Email Format isn't valid")
    private String email;

    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = USERNAME_REGEX, message = "Username Format isn't valid")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = PASSWORD_REGEX, message = "Password Format isn't valid")
    private String password;

    private String phone;
    private String firstName;
    private String lastName;
}
