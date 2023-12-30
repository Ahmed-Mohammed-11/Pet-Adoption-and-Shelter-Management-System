package com.example.backend.dao.implementation;

import com.example.backend.dao.Repository.CrudRepository;
import com.example.backend.dto.Response.FullShelterDTO;
import com.example.backend.dto.Response.ShelterMainInfoDTO;
import com.example.backend.exceptions.exception.ShelterNotFoundException;
import com.example.backend.model.Shelter;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
@AllArgsConstructor
public class ShelterRepositoryImpl implements CrudRepository<Shelter, Integer> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer save(Shelter shelter) {
        String sql = """
                INSERT INTO pet_adoption.shelter
                (name, location, phone, email, shelter_mgr_id)
                VALUES (?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, shelter.getName());
            pst.setString(2, shelter.getLocation());
            pst.setString(3, shelter.getPhone());
            pst.setString(4, shelter.getEmail());
            pst.setInt(5, shelter.getShelterMgrId());
            return pst;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public Optional<Shelter> findById(Integer shelterId) {
        String sql = """
                SELECT * FROM shelter
                WHERE shelter_id = ?
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shelter.class), shelterId)
                .stream()
                .findFirst();
    }

    public Optional<Shelter> findByShelterMgrId(Integer shelterMgrId) {
        String sql = """
                SELECT * FROM shelter
                WHERE shelter_mgr_id = ?
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shelter.class), shelterMgrId)
                .stream()
                .findFirst();
    }

    public Optional<Shelter> findByName(String shelterName) {
        String sql = """
                SELECT * FROM shelter
                WHERE name = ?
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shelter.class), shelterName)
                .stream()
                .findFirst();
    }

    @Override
    public List<Shelter> findAll() {
        String sql = """
                SELECT * FROM shelter
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shelter.class));
    }

    // TODO can keys be updated????
    @Override
    public void update(Shelter shelter) {
        String sql = """
                UPDATE pet_adoption.shelter
                SET name = ?, location = ?, phone = ?, email = ?
                WHERE shelter_id = ?
                """;

        jdbcTemplate.update(sql, shelter.getName(), shelter.getLocation(), shelter.getPhone(),
                shelter.getEmail(), shelter.getShelterId());
    }

    @Override
    public void deleteById(Integer shelterId) {
        String sql = """
                DELETE FROM pet_adoption.shelter
                WHERE shelter_id = ?
                """;

        jdbcTemplate.update(sql, shelterId);
    }

    @Override
    public void delete(Shelter shelter) {
        String sql = """
                DELETE FROM pet_adoption.shelter
                WHERE shelter_id = ?
                """;

        jdbcTemplate.update(sql, shelter.getShelterId());
    }

    public List<ShelterMainInfoDTO> findAllShelterMainInfo(int shelterManagerId) {
        String sql = """
                SELECT shelter_id,name,email,location,phone FROM shelter
                WHERE shelter_mgr_id = ?
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShelterMainInfoDTO.class), shelterManagerId)
                .stream()
                .toList();
    }

    public FullShelterDTO findFullShelterInfo(int shelterId) {

        String sql = """
                SELECT s.shelter_id, s.name, s.email, s.location, s.phone, u.user_id, u.email , u.userName, u.firstName, u.lastName, u.phone, u.role ,sm.staff_role FROM shelter s
                LEFT JOIN staff_member sm on s.shelter_id = sm.shelter_id
                JOIN pet_adoption.user u on u.user_id = sm.user_id
                WHERE s.shelter_id = ?
                """;

        return jdbcTemplate.query(sql, new FullShelterDTORowMapper(), shelterId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ShelterNotFoundException("Shelter not found"));
    }

}
