package com.example.backend.Service;

import com.example.backend.DAO.PetRepositoryImpl;
import com.example.backend.DTO.Request.PetDTO;
import com.example.backend.Enums.Behavior;
import com.example.backend.Model.Pet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetService {
    private PetRepositoryImpl petRepository;
    public void createPet(PetDTO petDTO) {
        Pet pet = Pet.builder()
                .name(petDTO.getName())
                .species(petDTO.getSpecies())
                .age(petDTO.getAge())
                .gender(petDTO.getGender())
                .description(petDTO.getDescription())
                .breed(petDTO.getBreed())
                .houseTraining(petDTO.isHouseTraining())
                .behavior(Behavior.valueOf(petDTO.getBehavior()))
                .build();
        petRepository.save(pet);
    }
}
