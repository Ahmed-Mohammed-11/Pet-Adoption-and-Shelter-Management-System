package com.example.backend.DAO;

import com.example.backend.DTO.Response.NotificationDTO;
import com.example.backend.Model.AdoptionRecord;

import java.util.List;

public interface AdoptionRepository extends CrudRepository<AdoptionRecord, Integer> {
    public List<NotificationDTO> findNotPendingRecords(Integer adopterUserId, int pageNumber);
}
