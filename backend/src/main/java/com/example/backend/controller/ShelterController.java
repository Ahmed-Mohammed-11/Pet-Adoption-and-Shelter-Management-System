package com.example.backend.controller;

import com.example.backend.dto.Request.ShelterDTO;
import com.example.backend.service.ShelterService;
import com.example.backend.constants.Endpoints;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoints.SHELTER)
public class ShelterController {

    private ShelterService shelterService;

    @PostMapping
    public ResponseEntity<String> createShelter(@RequestBody ShelterDTO shelterDTO) {
        shelterService.createShelter(shelterDTO);
        return ResponseEntity.ok("Shelter created!");
    }
}