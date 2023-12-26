package com.example.backend.Model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StaffMember extends User {
    private String shelterName;
    private String staffRole;
}
