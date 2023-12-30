package com.example.backend.dto.Response;


import com.example.backend.model.users.StaffMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FullShelterDTO {
    private int shelterId;
    private String name;
    private String email;
    private String location;
    private String phone;
    private List<StaffMemberDTO> staffMembers;
}
