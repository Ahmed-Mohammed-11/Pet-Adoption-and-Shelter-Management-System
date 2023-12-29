package com.example.backend.DAO;

import com.example.backend.DAO.AdoptionRepository;
import com.example.backend.DTO.Response.NotificationDTO;
import com.example.backend.Model.AdoptionRecord;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AdoptionRepositoryImpl implements AdoptionRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public void save(AdoptionRecord adoptionRecord) {
        String sql = "INSERT INTO pet_adoption.adoption_record " +
                "(pet_id, adopter_user_id, status, acceptance_date) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,adoptionRecord.getPetId(), adoptionRecord.getAdopterUserId(),
                adoptionRecord.getStatus(), adoptionRecord.getAcceptanceDate());
    }

    @Override
    public Optional<AdoptionRecord> findById(Integer petId) {
        String sql = "SELECT * FROM pet_adoption.adoption_record WHERE pet_id = ?";
        return jdbcTemplate
                .query(sql, new BeanPropertyRowMapper<>(AdoptionRecord.class) , petId)
                .stream()
                .findFirst();
    }

    @Override
    public List<AdoptionRecord> findAll() {
        String sql = "SELECT * FROM pet_adoption.adoption_record";
        return jdbcTemplate
                .query(sql, new BeanPropertyRowMapper<>(AdoptionRecord.class));
    }

    @Override
    public void update(AdoptionRecord adoptionRecord) {
        String sql = "UPDATE pet_adoption.adoption_record SET adopter_user_id = ?, status = ?, acceptance_date = ? WHERE pet_id = ?";
        jdbcTemplate.update(
                sql, adoptionRecord.getAdopterUserId(), adoptionRecord.getStatus(),
                adoptionRecord.getAcceptanceDate(), adoptionRecord.getPetId());
    }

    @Override
    public void deleteById(Integer petId) {
        String sql = "DELETE FROM pet_adoption.adoption_record WHERE pet_id = ?";
        jdbcTemplate.update(sql, petId);
    }

    @Override
    public void delete(AdoptionRecord adoptionRecord) {
        String sql = "DELETE FROM pet_adoption.adoption_record WHERE pet_id = ?";
        jdbcTemplate.update(sql, adoptionRecord.getPetId());
    }

    public List<NotificationDTO> findNotPendingRecords(Integer adopterUserId, int pageNumber) {
        int offset = (pageNumber - 1) * 50;

        String sql = "SELECT ar.status, p.name as petName, ar.acceptanceDate " +
                "FROM pet_adoption.adoption_record ar " +
                "JOIN pet_adoption.pet p ON ar.pet_id = p.pet_id " +
                "WHERE ar.adopter_user_id = ? AND ar.status != 'PENDING' " +
                "LIMIT ? OFFSET";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NotificationDTO.class), adopterUserId, 50, offset);
    }

}
