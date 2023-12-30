package com.example.backend.service;

import com.example.backend.dao.implementation.ShelterRepositoryImpl;
import com.example.backend.dao.implementation.StaffRepositoryImpl;
import com.example.backend.dto.Request.ShelterDTO;
import com.example.backend.dto.Response.FullShelterDTO;
import com.example.backend.dto.Response.ShelterMainInfoDTO;
import com.example.backend.model.Shelter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShelterService {

    private ShelterRepositoryImpl shelterRepository;
    private StaffRepositoryImpl staffRepository;

    public void createShelter(int shelterManagerId,ShelterDTO shelterDTO) {
        Shelter shelter = Shelter.builder()
                .name(shelterDTO.getName())
                .email(shelterDTO.getEmail())
                .location(shelterDTO.getLocation())
                .phone(shelterDTO.getPhone())
                .shelterMgrId(shelterManagerId)
                .build();

        shelterRepository.save(shelter);
    }

    public List<ShelterMainInfoDTO> getMainShelterInfo(int shelterManagerId){
       return shelterRepository.findAllShelterMainInfo(shelterManagerId);
    }

    public FullShelterDTO getFullShelterInfo(int shelterId) {
        return shelterRepository.findFullShelterInfo(shelterId);
    }

    public void updateMainShelterInfo(ShelterMainInfoDTO shelterMainInfoDTO) {
        Shelter shelter = Shelter.builder()
                .name(shelterMainInfoDTO.getName())
                .email(shelterMainInfoDTO.getEmail())
                .location(shelterMainInfoDTO.getLocation())
                .phone(shelterMainInfoDTO.getPhone())
                .build();
        shelterRepository.update(shelter);
    }

    public void deleteStaffMember(int staffId) {
        staffRepository.deleteById(staffId);
    }
}
