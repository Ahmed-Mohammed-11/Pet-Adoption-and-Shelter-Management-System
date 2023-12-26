package com.example.backend.Model.shelter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShelterId {
    private int shelterId;
    private int shelterMgrId;
}
