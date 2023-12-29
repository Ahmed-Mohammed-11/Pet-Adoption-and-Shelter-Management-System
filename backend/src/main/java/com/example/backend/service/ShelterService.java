package com.example.backend.service;

import com.example.backend.dao.implementation.ShelterRepositoryImpl;
import com.example.backend.dto.Request.ShelterDTO;
import com.example.backend.model.shelter.Shelter;
import com.example.backend.model.shelter.ShelterId;
import com.example.backend.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShelterService {

    private ShelterRepositoryImpl shelterRepository;
    private SecurityUtils securityUtils;

    public void createShelter(ShelterDTO shelterDTO) {

        int managerId = securityUtils.getCurrentUserId();

        Shelter shelter = Shelter.builder()
                .name(shelterDTO.getName())
                .email(shelterDTO.getEmail())
                .location(shelterDTO.getLocation())
                .phone(shelterDTO.getPhone())
                .shelterId(ShelterId.builder()
                        .shelterMgrId(managerId)
                        .build())
                .build();

        shelterRepository.save(shelter);
    }
}
