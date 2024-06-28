package com.pillgood.service;

import com.pillgood.dto.NutrientDto;
import com.pillgood.entity.Nutrient;
import com.pillgood.repository.NutrientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface NutrientService {
    NutrientDto createNutrient(NutrientDto nutrientDto);
    Optional<NutrientDto> getNutrientById(int id);
    List<NutrientDto> getAllNutrients();
    Optional<NutrientDto> updateNutrient(int id, NutrientDto nutrientDto);
    boolean deleteNutrient(int id);
}
