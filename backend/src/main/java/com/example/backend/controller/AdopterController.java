package com.example.backend.controller;

import com.example.backend.dto.Response.AdoptionDTO;
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

@RestController
@AllArgsConstructor
@RequestMapping("/adopter/")
public class AdopterController {
    private final AdopterService adopterService;
    private final PetService petService;

    @PostMapping("adopt")
    public ResponseEntity<String> adopt(@RequestParam int petId) {
        return this.adopterService.adopt(petId);
    }

    @PostMapping("cancelAdoption")
    public ResponseEntity<String> cancelAdoption(@RequestParam int petId) {
        return this.adopterService.cancelAdoption(petId);
    }

    @GetMapping("getNotifications")
    public ResponseEntity<List<NotificationDTO>> getNotifications(@RequestParam int pageNumber){
        List<NotificationDTO> notifications = adopterService.getNotifications(pageNumber);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("getAdoptionStatus")
    public ResponseEntity<AdoptionRecord> getAdoptionStatus(@RequestParam int petId){
        return adopterService.getAdoptionStatus(petId);
    }
    @GetMapping("pets")
    public ResponseEntity<Object> getPets(@RequestParam int shelterId, @RequestParam int pageNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.getPets(shelterId, pageNumber));
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
