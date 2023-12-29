package com.example.backend.DTO.Request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetDTO {
    private String name;
    private String species;
    private int age;
    private String gender;
    private String description;
    private String breed;
    private boolean houseTraining;
    private String behavior;
}
