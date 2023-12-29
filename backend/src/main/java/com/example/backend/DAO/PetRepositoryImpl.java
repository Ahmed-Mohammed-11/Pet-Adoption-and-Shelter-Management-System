package com.example.backend.DAO;

import com.example.backend.Model.Pet;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PetRepositoryImpl implements PetRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer save(Pet pet) {
        String sql= """
                INSERT INTO pet_adoption.pet
                (name, species, age, gender, description, breed, house_training, behavior, shelter_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql, pet.getName(), pet.getSpecies(), pet.getAge(), pet.getGender(),
                pet.getDescription(), pet.getBreed(), pet.isHouseTraining(), pet.getBehavior().toString(), pet.getShelterId());
        return pet.getPetId();
    }

    @Override
    public Optional<Pet> findById(Integer petId) {
        String sql = """
                SELECT * FROM pet_adoption.pet WHERE pet_id = ?  
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pet.class), petId)
                .stream()
                .findFirst();
    }

    @Override
    public List<Pet> findAll() {
        String sql = """
                SELECT * FROM pet_adoption.pet
                LIMIT 100
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pet.class));
    }

    @Override
    public int update(Pet pet) {
        String sql = """
                UPDATE pet_adoption.pet
                SET (?, ?, ?, ?, ?, ?, ?, ?, ?)
                WHERE pet_id = ?
                """;
        return jdbcTemplate.update(sql, pet.getName(), pet.getSpecies(), pet.getAge(), pet.getGender(),
                pet.getDescription(), pet.getBreed(), pet.isHouseTraining(), pet.getBehavior().toString(), pet.getShelterId(), pet.getPetId());
    }

    @Override
    public int deleteById(Integer petId) {
        String sql = """
                DELETE FROM pet_adoption.pet WHERE pet_id = ?
                """;
        return jdbcTemplate.update(sql, petId);
    }

    @Override
    public int delete(Pet pet) {
        return 0;
    }

    @Override
    public Optional<Pet> findByBreed(String breed) {
        String sql = """
                SELECT * FROM pet_adoption.pet WHERE breed = ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pet.class), breed)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Pet> findBySpecies(String species) {
        String sql = """
                SELECT * FROM pet_adoption.pet WHERE species = ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pet.class), species)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Pet> findByAge(int age) {
        String sql = """
                SELECT * FROM pet_adoption.pet WHERE age = ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pet.class), age)
                .stream()
                .findFirst();
    }
}
