package com.example.backend.controller;

import com.example.backend.constants.Endpoints;
import com.example.backend.dto.Request.ShelterDTO;
import com.example.backend.dto.Response.FullShelterDTO;
import com.example.backend.dto.Response.ShelterMainInfoDTO;
import com.example.backend.service.ShelterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoints.SHELTER)
public class ShelterController {

    private ShelterService shelterService;

    @PostMapping("/create")
    public ResponseEntity<String> createShelter(@AuthenticationPrincipal int shelterManagerId,
                                                @RequestBody ShelterDTO shelterDTO) {
        shelterService.createShelter(shelterManagerId,shelterDTO);
        return ResponseEntity.ok("Shelter created!");
    }

    @GetMapping("/get-shelters-info")
    public ResponseEntity<List<ShelterMainInfoDTO>> getMainShelterInfo(@AuthenticationPrincipal int shelterManagerId) {
        List<ShelterMainInfoDTO> shelterMainInfoDTOList = shelterService.getMainShelterInfo(shelterManagerId);
        return ResponseEntity.ok(shelterMainInfoDTOList);
    }

    @GetMapping("/get-full-shelter-info")
    public ResponseEntity<FullShelterDTO> getFullShelterInfo(@RequestParam int shelterId) {
        FullShelterDTO fullShelterDTO = shelterService.getFullShelterInfo(shelterId);
        return ResponseEntity.ok(fullShelterDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStaffMember(@RequestParam int staffId) {
        shelterService.deleteStaffMember(staffId);
        return ResponseEntity.ok("Staff member deleted successfully");
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateMainShelterInfo(@RequestBody ShelterMainInfoDTO shelterMainInfoDTO) {
        shelterService.updateMainShelterInfo(shelterMainInfoDTO);
        return ResponseEntity.ok("Shelter updated successfully");
    }

}
