//package com.example.backend.DAO.implementation;
//
//import com.example.backend.DAO.Repository.AdoptionRepository;
//import com.example.backend.DTO.Response.NotificationDTO;
//import com.example.backend.Model.adoptionRecord.AdoptionRecord;
//import com.example.backend.Model.adoptionRecord.RecordId;
//import lombok.AllArgsConstructor;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@AllArgsConstructor
//public class AdoptionRepositoryImpl implements AdoptionRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//
//    @Override
//    public RecordId save(AdoptionRecord adoptionRecord) {
//        String sql = "INSERT INTO pet_adoption.adoption_record " +
//                "(pet_id, adopter_user_id, status, acceptance_date) " +
//                "VALUES (?, ?, ?, ?)";
//        jdbcTemplate.update(sql,adoptionRecord.getRecordId().getAdopterUserId(), adoptionRecord.getRecordId().getAdopterUserId(),
//                adoptionRecord.getStatus(), adoptionRecord.getAcceptanceDate());
//    }
//
//    @Override
//    public Optional<AdoptionRecord> findById(Integer petId) {
//        String sql = "SELECT * FROM pet_adoption.adoption_record WHERE pet_id = ?";
//        return jdbcTemplate
//                .query(sql, new BeanPropertyRowMapper<>(AdoptionRecord.class) , petId)
//                .stream()
//                .findFirst();
//    }
//
//    @Override
//    public List<AdoptionRecord> findAll() {
//        String sql = "SELECT * FROM pet_adoption.adoption_record";
//        return jdbcTemplate
//                .query(sql, new BeanPropertyRowMapper<>(AdoptionRecord.class));
//    }
//
//    @Override
//    public void update(AdoptionRecord adoptionRecord) {
//        String sql = "UPDATE pet_adoption.adoption_record SET adopter_user_id = ?, status = ?, acceptance_date = ? WHERE pet_id = ?";
//        jdbcTemplate.update(
//                sql, adoptionRecord.getAdopterUserId(), adoptionRecord.getStatus(),
//                adoptionRecord.getAcceptanceDate(), adoptionRecord.getPetId());
//    }
//
//    @Override
//    public void deleteById(RecordId recordId) {
//        String sql = "DELETE FROM pet_adoption.adoption_record WHERE pet_id = ? AND adopter_user_id = ?";
//        jdbcTemplate.update(sql, recordId.getPetId(), recordId.getAdopterUserId());
//    }
//
//    @Override
//    public void delete(AdoptionRecord adoptionRecord) {
//        String sql = "DELETE FROM pet_adoption.adoption_record WHERE pet_id = ? AND adopter_user_id = ?";
//        jdbcTemplate.update(sql, adoptionRecord.getRecordId().getPetId(), adoptionRecord.getRecordId().getAdopterUserId());
//    }
//
//    public List<NotificationDTO> findNotPendingRecords(Integer adopterUserId, int pageNumber) {
//        int offset = (pageNumber - 1) * 50;
//
//        String sql = "SELECT ar.status, p.name as petName, ar.acceptanceDate " +
//                "FROM pet_adoption.adoption_record ar " +
//                "JOIN pet_adoption.pet p ON ar.pet_id = p.pet_id " +
//                "WHERE ar.adopter_user_id = ? AND ar.status != 'PENDING' " +
//                "LIMIT ? OFFSET";
//
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NotificationDTO.class), adopterUserId, 50, offset);
//    }
//
//}
