package com.example.backend.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShelterMainInfoDTO {
    private int shelterId;
    private String name;
    private String email;
    private String location;
    private String phone;
}
