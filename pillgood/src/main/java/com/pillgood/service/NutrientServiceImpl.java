package com.pillgood.service;

import com.pillgood.dto.NutrientDto;
import com.pillgood.entity.Nutrient;
import com.pillgood.repository.NutrientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NutrientServiceImpl implements NutrientService {

    private final NutrientRepository nutrientRepository;

    @Override
    public NutrientDto createNutrient(NutrientDto nutrientDto) {
        Nutrient nutrient = new Nutrient();
        nutrient.setNutrientName(nutrientDto.getNutrientName());
        nutrient.setDescription(nutrientDto.getDescription());
        nutrient = nutrientRepository.save(nutrient);
        nutrientDto.setNutrientId(nutrient.getNutrientId());
        nutrientDto.setDescription(nutrient.getDescription());
        return nutrientDto;
    }

    @Override
    public Optional<NutrientDto> getNutrientById(int id) {
        return nutrientRepository.findById(id)
                .map(nutrient -> new NutrientDto(
                        nutrient.getNutrientId(),
                        nutrient.getNutrientName(),
                        nutrient.getDescription()
                ));
    }

    @Override
    public List<NutrientDto> getAllNutrients() {
        List<Nutrient> nutrients = nutrientRepository.findAll();
        List<NutrientDto> nutrientDtos = new ArrayList<>();
        for (Nutrient nutrient : nutrients) {
            NutrientDto nutrientDto = new NutrientDto(
                    nutrient.getNutrientId(),
                    nutrient.getNutrientName(),
                    nutrient.getDescription()
            );
            nutrientDtos.add(nutrientDto);
        }
        return nutrientDtos;
    }

    @Override
    public Optional<NutrientDto> updateNutrient(int id, NutrientDto nutrientDto) {
        return nutrientRepository.findById(id)
                .map(nutrient -> {
                    nutrient.setNutrientName(nutrientDto.getNutrientName());
                    nutrient.setDescription(nutrientDto.getDescription());
                    nutrient = nutrientRepository.save(nutrient);
                    return new NutrientDto(nutrient.getNutrientId(), nutrient.getNutrientName(), nutrient.getDescription());
                });
    }

    @Override
    public boolean deleteNutrient(int id) {
        if (nutrientRepository.existsById(id)) {
            nutrientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
