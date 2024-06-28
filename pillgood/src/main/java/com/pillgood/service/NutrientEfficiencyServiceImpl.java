package com.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pillgood.dto.NutrientEfficiencyDto;
import com.pillgood.entity.Efficiency;
import com.pillgood.entity.Nutrient;
import com.pillgood.entity.NutrientEfficiency;
import com.pillgood.repository.NutrientEfficiencyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NutrientEfficiencyServiceImpl implements NutrientEfficiencyService {

    private final NutrientEfficiencyRepository nutrientEfficiencyRepository;

    @Override
    public List<NutrientEfficiencyDto> getAllNutrientEfficiencies() {
        return nutrientEfficiencyRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NutrientEfficiencyDto createNutrientEfficiency(NutrientEfficiencyDto nutrientEfficiencyDTO) {
        NutrientEfficiency nutrientEfficiency = convertToEntity(nutrientEfficiencyDTO);
        NutrientEfficiency savedNutrientEfficiency = nutrientEfficiencyRepository.save(nutrientEfficiency);
        return convertToDTO(savedNutrientEfficiency);
    }

    @Override
    public Optional<NutrientEfficiencyDto> updateNutrientEfficiency(int id, NutrientEfficiencyDto updatedNutrientEfficiencyDTO) {
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
    public NutrientEfficiencyDto convertToDTO(NutrientEfficiency nutrientEfficiency) {
        return new NutrientEfficiencyDto(
                nutrientEfficiency.getNutrientEfficiencyId(),
                nutrientEfficiency.getNutrient().getNutrientId(),
                nutrientEfficiency.getEfficiency().getEfficiencyId()
        );
    }

    @Override
    public NutrientEfficiency convertToEntity(NutrientEfficiencyDto nutrientEfficiencyDTO) {
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
