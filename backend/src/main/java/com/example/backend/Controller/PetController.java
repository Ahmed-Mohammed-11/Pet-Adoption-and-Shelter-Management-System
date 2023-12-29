package com.example.backend.Controller;

import com.example.backend.DTO.Request.PetDTO;
import com.example.backend.Service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/pets")
public class PetController {

    private PetService petService;

    @GetMapping
    public ResponseEntity<Object> getPets(){
        return ResponseEntity.status(HttpStatus.OK).body(petService.getPets());
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Object> getPet(@PathVariable Integer petId){
        return ResponseEntity.status(HttpStatus.OK).body(petService.getPet(petId));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPet(@RequestBody PetDTO petDTO){
        String petId = petService.createPet(petDTO).toString();
        return ResponseEntity.status(HttpStatus.CREATED).body(petId);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePet(@RequestBody PetDTO petDTO){
        petService.updatePet(petDTO);
        return ResponseEntity.ok("Pet updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePet(@RequestParam int petId){
        petService.deletePet(petId);
        return ResponseEntity.ok("Pet deleted successfully");
    }

}
