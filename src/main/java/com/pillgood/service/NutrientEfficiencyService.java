package com.pillgood.service;

import java.util.List;
import java.util.Optional;

import com.pillgood.dto.NutrientEfficiencyDto;
import com.pillgood.entity.NutrientEfficiency;

public interface NutrientEfficiencyService {
    List<NutrientEfficiencyDto> getAllNutrientEfficiencies();
    NutrientEfficiencyDto createNutrientEfficiency(NutrientEfficiencyDto nutrientEfficiencyDTO);
    Optional<NutrientEfficiencyDto> updateNutrientEfficiency(int id, NutrientEfficiencyDto updatedNutrientEfficiencyDTO);
    boolean deleteNutrientEfficiency(int id);
    NutrientEfficiencyDto convertToDTO(NutrientEfficiency nutrientEfficiency);
    NutrientEfficiency convertToEntity(NutrientEfficiencyDto nutrientEfficiencyDTO);
}
