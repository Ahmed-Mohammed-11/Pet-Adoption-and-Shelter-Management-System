package com.example.backend.DAO;

import com.example.backend.Model.Pet;

import java.util.Optional;

public interface PetRepository extends CrudRepository<Pet, Integer> {
    public Optional<Pet> findByBreed(String breed);
    public Optional<Pet> findBySpecies(String species);
    public Optional<Pet> findByAge(int age);

    // left filter by shelter location
}
