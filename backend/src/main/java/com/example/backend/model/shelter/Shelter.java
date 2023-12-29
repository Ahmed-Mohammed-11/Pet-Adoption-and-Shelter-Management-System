package com.example.backend.model.shelter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shelter {
    private ShelterId shelterId;
    private String name;
    private String location;
    private String phone;
    private String email;
}
