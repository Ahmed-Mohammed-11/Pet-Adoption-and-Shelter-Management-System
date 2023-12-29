package com.example.backend.DTO.Request;

import com.example.backend.Enums.Behaviour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDTO {
    private String name;
    private String species;
    private int age;
    private String gender;
    private String description;
    private String breed;
    private boolean houseTraining;
    private Behaviour behaviour;
    private int shelterId;
}