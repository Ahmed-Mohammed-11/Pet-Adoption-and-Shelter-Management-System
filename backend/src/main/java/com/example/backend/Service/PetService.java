package com.example.backend.Service;

import com.example.backend.DAO.PetRepositoryImpl;
import com.example.backend.DTO.Request.PetDTO;
import com.example.backend.Exceptions.PetNotFoundException;
import com.example.backend.Model.Pet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetService {

    private PetRepositoryImpl petRepository;

    public Object getPets() {
        return petRepository.findAll();
    }

    public Object getPet(int petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("Pet with id " + petId + " not found"));
        return PetDTO.builder()
                .name(pet.getName())
                .species(pet.getSpecies())
                .age(pet.getAge())
                .gender(pet.getGender())
                .description(pet.getDescription())
                .breed(pet.getBreed())
                .houseTraining(pet.isHouseTraining())
                .behaviour(pet.getBehaviour())
                .shelterId(pet.getShelterId())
                .build();
    }

    public Integer createPet(PetDTO petDTO) {
        Pet pet = Pet.builder()
                .name(petDTO.getName())
                .species(petDTO.getSpecies())
                .age(petDTO.getAge())
                .gender(petDTO.getGender())
                .description(petDTO.getDescription())
                .breed(petDTO.getBreed())
                .houseTraining(petDTO.isHouseTraining())
                .behaviour(petDTO.getBehaviour())
                .shelterId(petDTO.getShelterId())
                .build();
        return petRepository.save(pet);
    }

    public void updatePet(PetDTO petDTO) {

    }

    public void deletePet(int petId) {
        petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("Pet with id " + petId + " not found"));
        petRepository.deleteById(petId);
    }
}
