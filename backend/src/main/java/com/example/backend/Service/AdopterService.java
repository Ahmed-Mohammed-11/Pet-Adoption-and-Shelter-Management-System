package com.example.backend.Service;

import com.example.backend.DAO.AdoptionRepository;
import com.example.backend.DTO.Response.NotificationDTO;
import com.example.backend.Enums.AdoptionStatus;
import com.example.backend.Model.AdoptionRecord;
import com.example.backend.Util.SecurityUtils;
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
                .adopterUserId(userId)
                .petId(petId)
                .acceptanceDate(null)
                .status(AdoptionStatus.PENDING)
                .build();
        adoptionRepository.save(adoptionRecord);
        return ResponseEntity.ok("Adopted successfully");
    }

    public ResponseEntity<String> cancelAdoption(int petId) {
        //TODO: check if pet exists
        adoptionRepository.deleteById(petId);
        return ResponseEntity.ok("Adoption cancelled successfully");
    }

    public ResponseEntity<List<NotificationDTO>> getNotifications(int pageNumber) {
        int userId = securityUtils.getCurrentUserId();
        List<NotificationDTO> notificationDTOList = adoptionRepository.findNotPendingRecords(userId, pageNumber);
        return ResponseEntity.ok(notificationDTOList);
    }

    public ResponseEntity<AdoptionRecord> getAdoptionStatus(int petId) {
        //TODO: check if pet exists
        AdoptionRecord adoptionRecord = adoptionRepository.findById(petId).orElse(null);
        return ResponseEntity.ok(adoptionRecord);
    }
}
