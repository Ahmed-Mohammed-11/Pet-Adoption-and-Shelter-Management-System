package com.example.backend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionDTO {
    private String adopterName;
    private String petName;
    private Long adopterUserId;
    private Long petId;
}
