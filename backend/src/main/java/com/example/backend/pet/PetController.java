package com.example.backend.pet;

import com.example.backend.entities.Pet;
import com.example.backend.entities.dtos.PetDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class PetController {

    private PetDAO petDAO;

    @PostMapping("/pet")
    public ResponseEntity<Integer> createPet(@RequestBody Pet pet){
        return ResponseEntity.ok(petDAO.insertPet(pet));
    }

    @GetMapping("/pet")
    public ResponseEntity<List<Pet>> getPet(){
        return ResponseEntity.ok(petDAO.selectPet());
    }


    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello");
    }
}
