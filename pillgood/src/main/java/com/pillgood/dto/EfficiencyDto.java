package com.pillgood.dto;

import com.pillgood.entity.Efficiency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EfficiencyDto {
    private int efficiencyId;
    private String efficiencyName;
}