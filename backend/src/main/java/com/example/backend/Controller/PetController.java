package com.example.backend.Controller;

import com.example.backend.DTO.Request.PetDTO;
import com.example.backend.Service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.constants.Endpoints.*;

@RestController
@AllArgsConstructor
@RequestMapping(PETS)
public class PetController {

    private PetService petService;

    @GetMapping("/{petId}")
    public ResponseEntity<Object> getPet(@PathVariable Integer petId) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.getPet(petId));
    }

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

    @GetMapping()
    public ResponseEntity<Object> getPetsWithFilters(
            @RequestParam(name = "breed", required = false) String breed,
            @RequestParam(name = "species", required = false) String species,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "gender", required = false) String gender
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.getPetWithFilters(breed, species, age, gender));
    }
}
