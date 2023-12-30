package com.example.backend.service;

import com.example.backend.dao.implementation.PetRepositoryImpl;
import com.example.backend.dao.implementation.UserRepositoryImpl;
import com.example.backend.dto.Request.PetDTO;
import com.example.backend.enums.Behaviour;
import com.example.backend.exceptions.exception.PetNotFoundException;
import com.example.backend.model.Pet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetService {

    private PetRepositoryImpl petRepository;

    private Pet petBuilder(PetDTO petDTO) {
        return Pet.builder()
                .name(petDTO.getName())
                .species(petDTO.getSpecies())
                .age(petDTO.getAge())
                .gender(petDTO.getGender())
                .description(petDTO.getDescription())
                .breed(petDTO.getBreed())
                .houseTraining(petDTO.isHouseTraining())
                .behaviour(petDTO.getBehaviour())
                .shelterId(petDTO.getShelterId())
                .isFertilised(petDTO.getIsFertilised())
                .isVaccinated(petDTO.getIsVaccinated())
                .build();
    }

    public List<Pet> getPets(int shelterId, int pageNumber) {
        return petRepository.getPetsByShelterId(shelterId, pageNumber);
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
                .isFertilised(pet.getIsFertilised())
                .isVaccinated(pet.getIsVaccinated())
                .build();
    }

    public Integer createPet(PetDTO petDTO) {
        Pet pet = petBuilder(petDTO);
        return petRepository.save(pet);
    }

    public void updatePet(PetDTO petDTO, Integer petId) {
        Pet pet = petBuilder(petDTO);
        pet.setPetId(petId);
        petRepository.update(pet);
    }

    public void deletePet(int petId) {
        petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("Pet with id " + petId + " not found"));
        petRepository.deleteById(petId);
    }

    public List<Pet> getPetWithFilters(String breed, String species, Integer age, String gender, Boolean isFertilised, Boolean isVaccinated, Boolean houseTraining, Behaviour behaviour, Integer shelterId, Integer pageNumber) {
        return petRepository.filterBy(breed, species, age, gender, isFertilised, isVaccinated, houseTraining, behaviour, shelterId, pageNumber);
    }

    public void isExistingPet(int petId) {
        if(!petRepository.isPetExists(petId)) {
            throw new PetNotFoundException("Pet with id " + petId + " not found");
        }
    }
}
