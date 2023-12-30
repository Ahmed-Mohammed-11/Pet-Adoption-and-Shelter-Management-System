package com.example.backend.controller;

import com.example.backend.dto.Response.NotificationDTO;
import com.example.backend.enums.Behaviour;
import com.example.backend.model.AdoptionRecord;
import com.example.backend.model.Pet;
import com.example.backend.service.AdopterService;
import com.example.backend.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/adopter/")
public class AdopterController {
    private final AdopterService adopterService;
    private final PetService petService;

    @PostMapping("adopt")
    public ResponseEntity<String> adopt(@AuthenticationPrincipal int userId , @RequestParam int petId) {
        return this.adopterService.adopt(userId, petId);

    }

    @PostMapping("cancelAdoption")
    public ResponseEntity<String> cancelAdoption(@AuthenticationPrincipal int userId ,@RequestParam int petId) {
        return this.adopterService.cancelAdoption(userId, petId);
    }

    @GetMapping("getNotifications")
    public ResponseEntity<List<NotificationDTO>> getNotifications(@AuthenticationPrincipal int userId ,@RequestParam int pageNumber){
        List<NotificationDTO> notifications = adopterService.getNotifications(userId, pageNumber);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("getAdoptionStatus")
    public ResponseEntity<AdoptionRecord> getAdoptionStatus(@AuthenticationPrincipal int userId ,@RequestParam int petId){
        return adopterService.getAdoptionStatus(userId, petId);
    }
    @GetMapping("pets")
    public ResponseEntity<Object> getPets(@RequestParam int pageNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(
                petService.getPetWithFilters(null, null, null, null,
                        null, null, null, null, null, pageNumber));
    }

    @GetMapping("pets/filter")
    public ResponseEntity<List<Pet>> getPetsWithFilters(
            @RequestParam(name = "breed", required = false) String breed,
            @RequestParam(name = "species", required = false) String species,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "isVaccinated", required = false) Boolean isVaccinated,
            @RequestParam(name = "isFertilised", required = false) Boolean isFertilised,
            @RequestParam(name = "houseTraining", required = false) Boolean houseTraining,
            @RequestParam(name = "behaviour", required = false) Behaviour behaviour,
            @RequestParam(name = "pageNumber") Integer pageNumber,
            @RequestParam(name = "shelterId", required = false) Integer shelterId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.getPetWithFilters(breed, species, age, gender, isVaccinated, isFertilised, houseTraining, behaviour, shelterId, pageNumber));
    }

    @GetMapping("pets/{petId}")
    public ResponseEntity<Object> getPet(@PathVariable Integer petId) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.getPet(petId));
    }
}
