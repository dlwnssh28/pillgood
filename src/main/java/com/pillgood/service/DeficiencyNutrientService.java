package com.pillgood.service;

import com.pillgood.dto.DeficiencyNutrientDto;
import com.pillgood.entity.Deficiency;
import com.pillgood.entity.DeficiencyNutrient;
import com.pillgood.entity.Nutrient;
import com.pillgood.repository.DeficiencyNutrientRepository;
import com.pillgood.repository.DeficiencyRepository;
import com.pillgood.repository.NutrientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface DeficiencyNutrientService {
    Optional<DeficiencyNutrientDto> createDeficiencyNutrient(DeficiencyNutrientDto dto);
    Optional<DeficiencyNutrientDto> getDeficiencyNutrientById(int id);
    List<DeficiencyNutrientDto> getAllDeficiencyNutrients();
    boolean deleteDeficiencyNutrient(int id);
}

