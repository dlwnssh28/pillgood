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

@Service
@RequiredArgsConstructor
public class DeficiencyNutrientServiceImpl implements DeficiencyNutrientService {

    private final DeficiencyNutrientRepository deficiencyNutrientRepository;
    private final DeficiencyRepository deficiencyRepository;
    private final NutrientRepository nutrientRepository;

    @Override
    public Optional<DeficiencyNutrientDto> createDeficiencyNutrient(DeficiencyNutrientDto dto) {
        Optional<Deficiency> deficiencyOpt = deficiencyRepository.findById(dto.getDeficiencyId());
        Optional<Nutrient> nutrientOpt = nutrientRepository.findById(dto.getNutrientId());

        if (deficiencyOpt.isPresent() && nutrientOpt.isPresent()) {
            DeficiencyNutrient deficiencyNutrient = new DeficiencyNutrient();
            deficiencyNutrient.setDeficiency(deficiencyOpt.get());
            deficiencyNutrient.setNutrient(nutrientOpt.get());

            DeficiencyNutrient savedDeficiencyNutrient = deficiencyNutrientRepository.save(deficiencyNutrient);
            dto.setDeficiencyNutrientId(savedDeficiencyNutrient.getDeficiencyNutrientId());
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DeficiencyNutrientDto> getDeficiencyNutrientById(int id) {
        return deficiencyNutrientRepository.findById(id)
                .map(deficiencyNutrient -> new DeficiencyNutrientDto(
                        deficiencyNutrient.getDeficiencyNutrientId(),
                        deficiencyNutrient.getDeficiency().getDeficiencyId(),
                        deficiencyNutrient.getNutrient().getNutrientId()
                ));
    }

    @Override
    public List<DeficiencyNutrientDto> getAllDeficiencyNutrients() {
        return deficiencyNutrientRepository.findAll().stream()
                .map(deficiencyNutrient -> new DeficiencyNutrientDto(
                        deficiencyNutrient.getDeficiencyNutrientId(),
                        deficiencyNutrient.getDeficiency().getDeficiencyId(),
                        deficiencyNutrient.getNutrient().getNutrientId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteDeficiencyNutrient(int id) {
        if (deficiencyNutrientRepository.existsById(id)) {
            deficiencyNutrientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
