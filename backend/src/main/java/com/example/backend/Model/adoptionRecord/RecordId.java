package com.example.backend.Model.adoptionRecord;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordId {
    private int adopterUserId;
    private int petId;
}
