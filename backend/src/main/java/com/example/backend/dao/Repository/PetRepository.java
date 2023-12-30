package com.example.backend.dao.Repository;

import com.example.backend.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends CrudRepository<Pet, Integer> {
    public Optional<Pet> findByBreed(String breed);
    public Optional<Pet> findBySpecies(String species);
    public Optional<Pet> findByAge(int age);

    public boolean isPetExists(Integer petId);

    public List<Pet> getPetsByShelterId(Integer shelterId, int pageNumber);

    public void removePetFromShelter(Integer petId);

    // left filter by shelter location
}