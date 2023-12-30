package com.example.backend.service;


import com.example.backend.dao.Repository.AdoptionRepository;
import com.example.backend.dao.Repository.PetRepository;
import com.example.backend.dto.Response.AdoptionDTO;
import com.example.backend.enums.AdoptionStatus;
import com.example.backend.exceptions.exception.AdoptionRecordNotFoundException;
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
    private final AdoptionRepository adoptionRepository;
    private final PetRepository petRepository;

    public ResponseEntity<String> changeAdoptionStatus(int adopterId,int petId, AdoptionStatus status) {
        AdoptionRecord adoptionRecord = adoptionRepository.findRecordById(new RecordId(adopterId,petId));
        if (adoptionRecord == null) {
            throw new AdoptionRecordNotFoundException("Adoption record with petId "+petId+" and adopterId " + adopterId + " not found");
        }
        adoptionRecord.setStatus(status);
        if (status == AdoptionStatus.ACCEPTED) {
            adoptionRecord.setAcceptanceDate(new java.sql.Date(System.currentTimeMillis()));
            this.petRepository.removePetFromShelter(petId);
        }
        adoptionRecord.setRecordId(new RecordId(adopterId, petId));
        this.adoptionRepository.update(adoptionRecord);
        return ResponseEntity.ok("Adoption status changed successfully");
    }

    //don't use this
    public ResponseEntity<List<AdoptionRecord>> getALLAdoptionRecords() {
        List<AdoptionRecord> adoptionRecordList = adoptionRepository.findAll();
        return ResponseEntity.ok(adoptionRecordList);
    }

    public ResponseEntity<List<AdoptionDTO>> getAdoptionRecords(int shelterId, int pageNumber) {
        List<AdoptionDTO> adoptionRecordList = adoptionRepository.findPendingRecords(shelterId, pageNumber);
        return ResponseEntity.ok(adoptionRecordList);
    }
}
