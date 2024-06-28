package com.pillgood.service;

import com.pillgood.dto.DeficiencyDto;
import com.pillgood.entity.Deficiency;
import com.pillgood.repository.DeficiencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeficiencyServiceImpl implements DeficiencyService {

    @Autowired
    private final DeficiencyRepository deficiencyRepository;

    @Override
    public DeficiencyDto createDeficiency(DeficiencyDto deficiencyDto){
        Deficiency deficiency = new Deficiency();
        deficiency.setDeficiencyName(deficiencyDto.getDeficiencyName());
        deficiency = deficiencyRepository.save(deficiency);
        deficiencyDto.setDeficiencyId(deficiency.getDeficiencyId());
        return deficiencyDto;
    }

    @Override
    public Optional<DeficiencyDto> getDeficiencyById(int id) {
        return deficiencyRepository.findById(id)
                .map(deficiency -> new DeficiencyDto(
                        deficiency.getDeficiencyId(),
                        deficiency.getDeficiencyName()
                ));
    }

    @Override
    public List<DeficiencyDto> getAllDeficiencies() {
        List<Deficiency> deficiencies = deficiencyRepository.findAll();
        List<DeficiencyDto> deficiencyDtos = new ArrayList<>();
        for (Deficiency deficiency : deficiencies) {
            DeficiencyDto deficiencyDto = new DeficiencyDto(
                    deficiency.getDeficiencyId(),
                    deficiency.getDeficiencyName()
            );
            deficiencyDtos.add(deficiencyDto);
        }
        return deficiencyDtos;
    }

    @Override
    public Optional<DeficiencyDto> updateDeficiency(int id, DeficiencyDto deficiencyDto) {
        return deficiencyRepository.findById(id)
                .map(deficiency -> {
                    deficiency.setDeficiencyName(deficiencyDto.getDeficiencyName());
                    deficiency = deficiencyRepository.save(deficiency);
                    return new DeficiencyDto(deficiency.getDeficiencyId(), deficiency.getDeficiencyName());
                });
    }

    @Override
    public boolean deleteDeficiency(int id) {
        if (deficiencyRepository.existsById(id)) {
            deficiencyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
