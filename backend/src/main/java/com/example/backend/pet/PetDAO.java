package com.example.backend.pet;

import com.example.backend.entities.Pet;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class PetDAO {
    private final JdbcTemplate jdbcTemplate;

    public int insertPet(Pet pet) {
        String sql = """
                INSERT INTO pet(name, species, age, gender, description, breed, house_training, behaviour)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(sql, pet.name(), pet.species(), pet.age(), pet.gender(), pet.description(), pet.breed(), pet.house_training(), "Playful");
    }

    public List<Pet> selectPet() {
        String sql = """
                SELECT * FROM pet;
                """;

        return jdbcTemplate.query(sql, new PetRowMapper());
    }

}
