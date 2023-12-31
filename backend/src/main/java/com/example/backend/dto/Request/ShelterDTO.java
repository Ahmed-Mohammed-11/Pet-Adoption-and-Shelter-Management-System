package com.example.backend.dto.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShelterDTO {
    private String name;
    private String email;
    private String location;
    private String phone;
}
