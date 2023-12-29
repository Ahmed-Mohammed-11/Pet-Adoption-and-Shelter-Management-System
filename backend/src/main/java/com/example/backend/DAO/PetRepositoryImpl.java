package com.example.backend.DAO;

import com.example.backend.Enums.Behaviour;
import com.example.backend.Model.Pet;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PetRepositoryImpl implements PetRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer save(Pet pet) {
        //return pet id to be used in the adoption table
        String sql= """
                INSERT INTO pet_adoption.pet
                (name, species, age, gender, description, breed, house_training, behaviour, shelter_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, pet.getName());
            pst.setString(2, pet.getSpecies());
            pst.setInt(3, pet.getAge());
            pst.setString(4, pet.getGender());
            pst.setString(5, pet.getDescription());
            pst.setString(6, pet.getBreed());
            pst.setBoolean(7, pet.isHouseTraining());
            pst.setString(8, pet.getBehaviour().toString());
            pst.setInt(9, pet.getShelterId());
            return pst;
        }, keyHolder);

        return keyHolder.getKey().intValue();
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
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pet.class));
    }

    @Override
    public void update(Pet pet) {
        String sql = """
                UPDATE pet_adoption.pet
                SET name = ?,
                species = ?,
                age = ?,
                gender = ?,
                description = ?,
                breed = ?,
                house_training = ?,
                behaviour = ?,
                shelter_id = ?
                WHERE pet_id = ?
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, pet.getName());
            pst.setString(2, pet.getSpecies());
            pst.setInt(3, pet.getAge());
            pst.setString(4, pet.getGender());
            pst.setString(5, pet.getDescription());
            pst.setString(6, pet.getBreed());
            pst.setBoolean(7, pet.isHouseTraining());
            pst.setString(8, pet.getBehaviour().toString());
            pst.setInt(9, pet.getShelterId());
            pst.setInt(10, pet.getPetId());
            return pst;
        }, keyHolder);
    }

    @Override
    public void deleteById(Integer petId) {
        String sql = """
                DELETE FROM pet_adoption.pet WHERE pet_id = ?
                """;
        jdbcTemplate.update(sql, petId);
    }

    @Override
    public void delete(Pet pet) {
        //not implemented
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

    public Object filterBy(String breed, String species, Integer age, String gender) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM pet_adoption.pet WHERE 1=1");
        queryBuilder.append((breed != null) ? " AND breed = '" + breed + "'" : "");
        queryBuilder.append((species != null) ? " AND species = '" + species + "'" : "");
        queryBuilder.append((age != null) ? " AND age = '" + age + "'" : "");
        queryBuilder.append((age != null) ? " AND gender = '" + gender + "'" : "");

        String sql = queryBuilder.toString();

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pet.class));
    }
}
