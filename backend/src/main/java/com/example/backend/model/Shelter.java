package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shelter {
    private int shelterId;
    private int shelterMgrId;
    private String name;
    private String location;
    private String phone;
    private String email;
}
