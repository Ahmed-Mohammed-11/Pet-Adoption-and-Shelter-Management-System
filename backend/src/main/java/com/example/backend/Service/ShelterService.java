package com.example.backend.Service;

import com.example.backend.DAO.ShelterRepositoryImpl;
import com.example.backend.DTO.Request.ShelterDTO;
import com.example.backend.Model.shelter.Shelter;
import com.example.backend.Model.shelter.ShelterId;
import com.example.backend.Util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextImpl;
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
