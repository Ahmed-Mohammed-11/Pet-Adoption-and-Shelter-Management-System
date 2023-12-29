package com.example.backend.DAO.Repository;

import com.example.backend.DTO.Response.NotificationDTO;
import com.example.backend.Model.adoptionRecord.AdoptionRecord;
import com.example.backend.Model.adoptionRecord.RecordId;

import java.util.List;

public interface AdoptionRepository extends CrudRepository<AdoptionRecord, RecordId> {
    public List<NotificationDTO> findNotPendingRecords(Integer adopterUserId, int pageNumber);
}
