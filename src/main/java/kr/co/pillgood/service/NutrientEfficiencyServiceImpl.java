package kr.co.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kr.co.pillgood.dto.NutrientEfficiencyDTO;
import kr.co.pillgood.entity.Efficiency;
import kr.co.pillgood.entity.Nutrient;
import kr.co.pillgood.entity.NutrientEfficiency;
import kr.co.pillgood.repository.NutrientEfficiencyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class NutrientEfficiencyServiceImpl implements NutrientEfficiencyService {

    private final NutrientEfficiencyRepository nutrientEfficiencyRepository;

    @Override
    public List<NutrientEfficiencyDTO> getAllNutrientEfficiencies() {
        return nutrientEfficiencyRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NutrientEfficiencyDTO createNutrientEfficiency(NutrientEfficiencyDTO nutrientEfficiencyDTO) {
        NutrientEfficiency nutrientEfficiency = convertToEntity(nutrientEfficiencyDTO);
        NutrientEfficiency savedNutrientEfficiency = nutrientEfficiencyRepository.save(nutrientEfficiency);
        return convertToDTO(savedNutrientEfficiency);
    }

    @Override
    public Optional<NutrientEfficiencyDTO> updateNutrientEfficiency(int id, NutrientEfficiencyDTO updatedNutrientEfficiencyDTO) {
        return nutrientEfficiencyRepository.findById(id)
                .map(nutrientEfficiency -> {
                    nutrientEfficiency.setNutrient(new Nutrient(updatedNutrientEfficiencyDTO.getNutrientId()));
                    nutrientEfficiency.setEfficiency(new Efficiency(updatedNutrientEfficiencyDTO.getEfficiencyId()));
                    NutrientEfficiency updatedNutrientEfficiency = nutrientEfficiencyRepository.save(nutrientEfficiency);
                    return convertToDTO(updatedNutrientEfficiency);
                });
    }

    @Override
    public boolean deleteNutrientEfficiency(int id) {
        if (nutrientEfficiencyRepository.existsById(id)) {
            nutrientEfficiencyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public NutrientEfficiencyDTO convertToDTO(NutrientEfficiency nutrientEfficiency) {
        return new NutrientEfficiencyDTO(
                nutrientEfficiency.getNutrientEfficiencyId(),
                nutrientEfficiency.getNutrient().getNutrientId(),
                nutrientEfficiency.getEfficiency().getEfficiencyId()
        );
    }

    @Override
    public NutrientEfficiency convertToEntity(NutrientEfficiencyDTO nutrientEfficiencyDTO) {
        Nutrient nutrient = new Nutrient();
        nutrient.setNutrientId(nutrientEfficiencyDTO.getNutrientId());

        Efficiency efficiency = new Efficiency();
        efficiency.setEfficiencyId(nutrientEfficiencyDTO.getEfficiencyId());

        return new NutrientEfficiency(
                nutrientEfficiencyDTO.getNutrientEfficiencyId(),
                nutrient,
                efficiency
        );
    }
}
