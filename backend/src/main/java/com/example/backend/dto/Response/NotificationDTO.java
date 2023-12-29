package com.example.backend.dto.Response;

import com.example.backend.enums.AdoptionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private AdoptionStatus status;
    private String petName;
    private LocalDate acceptanceDate;
}
