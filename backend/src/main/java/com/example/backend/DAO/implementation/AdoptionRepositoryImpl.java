package com.example.backend.DAO.implementation;

import com.example.backend.DAO.Repository.AdoptionRepository;
import com.example.backend.DTO.Response.NotificationDTO;
import com.example.backend.Model.adoptionRecord.AdoptionRecord;
import com.example.backend.Model.adoptionRecord.RecordId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AdoptionRepositoryImpl implements AdoptionRepository {

    private final int pageSize;
    private final JdbcTemplate jdbcTemplate;

    public AdoptionRepositoryImpl(
            @Value("${page-size}") int pageSize,
            JdbcTemplate jdbcTemplate
    ){
        this.pageSize = pageSize;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public RecordId save(AdoptionRecord adoptionRecord) {
        String sql = "INSERT INTO pet_adoption.adoption_record " +
                "(pet_id, adopter_id, status, acceptance_date) " +
                "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, adoptionRecord.getRecordId().getAdopterUserId());
            pst.setInt(2, adoptionRecord.getRecordId().getPetId());
            pst.setString(3, adoptionRecord.getStatus().name());
            pst.setDate(4, adoptionRecord.getAcceptanceDate());
            return pst;
        }, keyHolder);

        return RecordId.builder()
                .adopterUserId(Objects.requireNonNull(keyHolder.getKey()).intValue())
                .petId(adoptionRecord.getRecordId().getPetId())
                .build();
    }

    @Override
    public Optional<AdoptionRecord> findById(RecordId recordId) {
        String sql = "SELECT * FROM pet_adoption.adoption_record WHERE pet_id = ? AND adopter_id = ?";
        return jdbcTemplate
                .query(sql, new BeanPropertyRowMapper<>(AdoptionRecord.class) , recordId.getPetId(), recordId.getAdopterUserId())
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
        String sql = "UPDATE pet_adoption.adoption_record SET status = ?, acceptance_date = ? WHERE pet_id = ? AND adopter_id = ?";
        jdbcTemplate.update(
                sql, adoptionRecord.getStatus(), adoptionRecord.getAcceptanceDate(),
                adoptionRecord.getRecordId().getPetId() , adoptionRecord.getRecordId().getAdopterUserId());
    }

    @Override
    public void deleteById(RecordId recordId) {
        String sql = "DELETE FROM pet_adoption.adoption_record WHERE pet_id = ? AND adopter_id = ?";
        jdbcTemplate.update(sql, recordId.getPetId(), recordId.getAdopterUserId());
    }

    @Override
    public void delete(AdoptionRecord adoptionRecord) {
        String sql = "DELETE FROM pet_adoption.adoption_record WHERE pet_id = ? AND adopter_id = ?";
        jdbcTemplate.update(sql, adoptionRecord.getRecordId().getPetId(), adoptionRecord.getRecordId().getAdopterUserId());
    }

    public List<NotificationDTO> findNotPendingRecords(Integer adopterUserId, int pageNumber) {
        int offset = (pageNumber - 1) * pageSize;

        String sql = "SELECT ar.status, p.name as petName, ar.acceptance_date " +
                "FROM pet_adoption.adoption_record ar " +
                "JOIN pet_adoption.pet p ON ar.pet_id = p.pet_id " +
                "WHERE ar.adopter_id = ? AND ar.status != 'PENDING' " +
                "LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NotificationDTO.class), adopterUserId, pageSize, offset);
    }

}
