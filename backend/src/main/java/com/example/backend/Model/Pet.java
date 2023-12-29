package com.example.backend.Model;

import com.example.backend.Enums.Behavior;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private int petId;
    private String name;
    private String species;
    private int age;
    private String gender;
    private String description;
    private String breed;
    private boolean houseTraining;
    private Behavior behavior;
    private int shelterId;
}
