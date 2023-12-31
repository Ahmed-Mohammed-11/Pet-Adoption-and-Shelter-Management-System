package com.example.backend.model.users;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StaffMember extends User {
    private int shelterId;
    private String staffRole;
}


