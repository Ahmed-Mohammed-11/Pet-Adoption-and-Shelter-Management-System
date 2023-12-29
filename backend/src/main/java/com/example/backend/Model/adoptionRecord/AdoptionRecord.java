package com.example.backend.Model;

import com.example.backend.Enums.AdoptionStatus;
import com.example.backend.Model.adoptionRecord.RecordId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionRecord {
    private RecordId recordId;
    private AdoptionStatus status;
    private LocalDate acceptanceDate;
}
