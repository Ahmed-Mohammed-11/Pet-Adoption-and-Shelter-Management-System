package com.example.backend.dto.Response;

import com.example.backend.enums.Role;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffMemberDTO {
    protected int userId;
    protected String username;
    protected String email;
    protected String phone;
    protected String firstName;
    protected String lastName;
    protected Role role;
    private String staffRole;
}
