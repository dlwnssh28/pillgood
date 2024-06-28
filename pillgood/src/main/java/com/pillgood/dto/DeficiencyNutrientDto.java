package com.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeficiencyNutrientDto {
    private int deficiencyNutrientId;
    private int deficiencyId;
    private int nutrientId;
}
