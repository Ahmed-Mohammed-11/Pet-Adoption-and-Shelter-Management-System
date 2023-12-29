package com.example.backend.service;

import com.example.backend.dao.Repository.AdoptionRepository;
import com.example.backend.dto.Response.NotificationDTO;
import com.example.backend.enums.AdoptionStatus;
import com.example.backend.model.AdoptionRecord;
import com.example.backend.model.adoptionRecord.RecordId;
import com.example.backend.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class AdopterService {

    private final SecurityUtils securityUtils;
    private final AdoptionRepository adoptionRepository;

    public ResponseEntity<String> adopt(int petId){
        int userId = securityUtils.getCurrentUserId();
        //TODO: check if pet exists
        AdoptionRecord adoptionRecord = AdoptionRecord.builder()
                .recordId(new RecordId(userId, petId))
                .acceptanceDate(null)
                .status(AdoptionStatus.PENDING)
                .build();
        adoptionRepository.save(adoptionRecord);
        return ResponseEntity.ok("Adopted successfully");
    }

    public ResponseEntity<String> cancelAdoption(int petId) {
        int userId = securityUtils.getCurrentUserId();
        //TODO: check if pet exists
        adoptionRepository.deleteById(new RecordId(userId, petId));
        return ResponseEntity.ok("Adoption cancelled successfully");
    }

    public List<NotificationDTO> getNotifications(int pageNumber) {
        int userId = securityUtils.getCurrentUserId();
        return adoptionRepository.findNotPendingRecords(userId, pageNumber);
    }

    public ResponseEntity<AdoptionRecord> getAdoptionStatus(int petId) {
        int userId = securityUtils.getCurrentUserId();
        //TODO: check if pet exists
        AdoptionRecord adoptionRecord = adoptionRepository.findById(new RecordId(userId,petId)).orElse(null);
        return ResponseEntity.ok(adoptionRecord);
    }
}
