package com.example.backend.DAO;

import com.example.backend.Model.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.sql.In;
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
    public void save(Pet pet) {
        String sql= """
                INSERT INTO pet_adoption.pet
                (name, species, age, gender, description, breed, house_training, behavior, shelter_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql, pet.getName(), pet.getSpecies(), pet.getAge(), pet.getGender(),
                pet.getDescription(), pet.getBreed(), pet.isHouseTraining(), pet.getBehavior().toString(), pet.getShelterId());
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
    public void update(Pet entity) {

    }

    @Override
    public void deleteById(Integer petId) {
    }

    @Override
    public void delete(Pet entity) {

    }
}
