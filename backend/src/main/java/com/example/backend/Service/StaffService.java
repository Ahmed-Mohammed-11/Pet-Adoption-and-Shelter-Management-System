package com.example.backend.Service;


import com.example.backend.DAO.Repository.AdoptionRepository;
import com.example.backend.Enums.AdoptionStatus;
import com.example.backend.Model.adoptionRecord.AdoptionRecord;
import com.example.backend.Model.adoptionRecord.RecordId;
import com.example.backend.Util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StaffService {
    private final SecurityUtils securityUtils;
    private final AdoptionRepository adoptionRepository;

    public ResponseEntity<String> changeAdoptionStatus(int adopterId,int petId, AdoptionStatus status) {
        AdoptionRecord adoptionRecord = adoptionRepository.findById(new RecordId(adopterId,petId)).orElse(null);
        if (adoptionRecord == null) {
            // TODO: throw exception
        }
        adoptionRecord.setStatus(status);
        adoptionRepository.save(adoptionRecord);
        return ResponseEntity.ok("Adoption status changed successfully");
    }

    public ResponseEntity<List<AdoptionRecord>> getAdoptionRecords() {
        List<AdoptionRecord> adoptionRecordList = adoptionRepository.findAll();
        return ResponseEntity.ok(adoptionRecordList);
    }
}
