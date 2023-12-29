package com.example.backend.service;


import com.example.backend.dao.Repository.AdoptionRepository;
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
