package com.example.backend.Model.adoptionRecord;

import com.example.backend.Enums.AdoptionStatus;
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
