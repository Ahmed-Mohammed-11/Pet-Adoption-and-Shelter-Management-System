package com.example.backend.DAO.implementation;

import com.example.backend.DAO.Repository.CrudRepository;
import com.example.backend.Model.shelter.Shelter;
import com.example.backend.Model.shelter.ShelterId;
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
public class ShelterRepositoryImpl implements CrudRepository<Shelter, ShelterId> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public ShelterId save(Shelter shelter) {
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
            pst.setInt(5, shelter.getShelterId().getShelterMgrId());
            return pst;
        }, keyHolder);

        return new ShelterId().builder()
                .shelterId(keyHolder.getKey().intValue())
                .shelterMgrId(shelter.getShelterId().getShelterMgrId())
                .build();
    }

    @Override
    public Optional<Shelter> findById(ShelterId shelterId) {
        String sql = """
                SELECT * FROM shelter
                WHERE shelter_mgr_id = ? AND shelter_id = ?
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shelter.class), shelterId.getShelterMgrId(), shelterId.getShelterId())
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
                WHERE shelter_mgr_id = ? AND shelter_id = ?
                """;

        jdbcTemplate.update(sql, shelter.getName(), shelter.getLocation(), shelter.getPhone(),
                shelter.getEmail(), shelter.getShelterId().getShelterMgrId(), shelter.getShelterId().getShelterId());
    }

    @Override
    public void deleteById(ShelterId shelterId) {
        String sql = """
                DELETE FROM pet_adoption.shelter
                WHERE shelter_mgr_id = ? AND shelter_id = ?
                """;

        jdbcTemplate.update(sql, shelterId.getShelterMgrId(), shelterId.getShelterId());
    }

    @Override
    public void delete(Shelter entity) {
        String sql = """
                DELETE FROM pet_adoption.shelter
                WHERE shelter_mgr_id = ? AND shelter_id = ?
                """;

        jdbcTemplate.update(sql, entity.getShelterId().getShelterMgrId(), entity.getShelterId().getShelterId());
    }

}
