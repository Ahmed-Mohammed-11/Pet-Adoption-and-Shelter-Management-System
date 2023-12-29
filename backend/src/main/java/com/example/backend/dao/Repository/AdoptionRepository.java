package com.example.backend.dao.Repository;

import com.example.backend.dto.Response.NotificationDTO;
import com.example.backend.model.AdoptionRecord;
import com.example.backend.model.Pet;
import com.example.backend.model.adoptionRecord.RecordId;

import java.util.List;
import java.util.Optional;

public interface AdoptionRepository extends CrudRepository<AdoptionRecord, RecordId> {
    public List<NotificationDTO> findNotPendingRecords(Integer adopterUserId, int pageNumber);


}
