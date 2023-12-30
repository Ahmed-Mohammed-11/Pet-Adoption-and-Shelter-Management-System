package com.example.backend.controller;

import com.example.backend.dto.Request.PetDTO;
import com.example.backend.dto.Response.AdoptionDTO;
import com.example.backend.enums.AdoptionStatus;
import com.example.backend.enums.Behaviour;
import com.example.backend.model.AdoptionRecord;
import com.example.backend.model.Pet;
import com.example.backend.service.PetService;
import com.example.backend.service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backend.constants.Endpoints.*;

@RestController
@AllArgsConstructor
@RequestMapping("/staff/")
public class StaffController {
    private final PetService petService;
    private final StaffService staffService;

    @PostMapping(CREATE_PET)
    public ResponseEntity<String> createPet(@RequestBody PetDTO petDTO) {
        String petId = petService.createPet(petDTO).toString();
        return ResponseEntity.status(HttpStatus.CREATED).body(petId);
    }

    @PutMapping(UPDATE_PET + "/{petId}")
    public ResponseEntity<String> updatePet(@RequestBody PetDTO petDTO, @PathVariable Integer petId) {
        petService.updatePet(petDTO, petId);
        return ResponseEntity.status(HttpStatus.OK).body("Pet updated successfully");
    }

    @DeleteMapping(DELETE_PET + "/{petId}")
    public ResponseEntity<String> deletePet(@PathVariable Integer petId) {
        petService.deletePet(petId);
        return ResponseEntity.status(HttpStatus.OK).body("Pet deleted successfully");
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

    @PostMapping("pets/changeAdoptionStatus")
    public ResponseEntity<String> changeAdoptionStatus(@RequestParam int adopterId, @RequestParam int petId, @RequestParam AdoptionStatus status) {
        return this.staffService.changeAdoptionStatus(adopterId,petId,status);
    }

    @GetMapping("pets/getAdoptionRecords")
    public ResponseEntity<List<AdoptionDTO>> getALLAdoptionRecords(@RequestParam int shelterId, int pageNumber) {
        return this.staffService.getAdoptionRecords(shelterId, pageNumber);
    }
}
