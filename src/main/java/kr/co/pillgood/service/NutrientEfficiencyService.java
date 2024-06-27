package kr.co.pillgood.service;

import java.util.List;
import java.util.Optional;

import kr.co.pillgood.dto.NutrientEfficiencyDTO;
import kr.co.pillgood.entity.NutrientEfficiency;

public interface NutrientEfficiencyService {
    List<NutrientEfficiencyDTO> getAllNutrientEfficiencies();
    NutrientEfficiencyDTO createNutrientEfficiency(NutrientEfficiencyDTO nutrientEfficiencyDTO);
    Optional<NutrientEfficiencyDTO> updateNutrientEfficiency(int id, NutrientEfficiencyDTO updatedNutrientEfficiencyDTO);
    boolean deleteNutrientEfficiency(int id);
    NutrientEfficiencyDTO convertToDTO(NutrientEfficiency nutrientEfficiency);
    NutrientEfficiency convertToEntity(NutrientEfficiencyDTO nutrientEfficiencyDTO);
}
