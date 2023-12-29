package com.example.backend.model;

import com.example.backend.enums.AdoptionStatus;
import com.example.backend.model.adoptionRecord.RecordId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionRecord {
    private RecordId recordId;
    private AdoptionStatus status;
    private Date acceptanceDate;
}
