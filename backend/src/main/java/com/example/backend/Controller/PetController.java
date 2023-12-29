package com.example.backend.Controller;

import com.example.backend.DTO.Request.PetDTO;
import com.example.backend.Service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;

    @GetMapping
    public String test(){
        return "Hello World";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPet(@RequestBody PetDTO petDTO){
        petService.createPet(petDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pet created successfully");
    }

//    @PutMapping("/update")
//    public ResponseEntity<String> updatePet(@RequestBody PetDTO petDTO){
//        petService.updatePet(petDTO);
//        return ResponseEntity.ok("Pet updated successfully");
//    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deletePet(@RequestParam int petId){
//        petService.deletePet(petId);
//        return ResponseEntity.ok("Pet deleted successfully");
//    }

}
