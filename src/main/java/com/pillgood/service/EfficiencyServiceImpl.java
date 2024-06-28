package com.pillgood.service;

import com.pillgood.dto.EfficiencyDto;
import com.pillgood.entity.Efficiency;
import com.pillgood.repository.EfficiencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EfficiencyServiceImpl implements EfficiencyService {

    @Autowired
    private final EfficiencyRepository efficiencyRepository;

    @Override
    public EfficiencyDto createEfficiency(EfficiencyDto efficiencyDto){
        Efficiency efficiency = new Efficiency();
        efficiency.setEfficiencyName(efficiencyDto.getEfficiencyName());
        efficiency = efficiencyRepository.save(efficiency);
        efficiencyDto.setEfficiencyId(efficiency.getEfficiencyId());
        return efficiencyDto;
    }

    @Override
    public Optional<EfficiencyDto> getEfficiencyById(int id) {
        return efficiencyRepository.findById(id)
                .map(efficiency -> new EfficiencyDto(
                        efficiency.getEfficiencyId(),
                        efficiency.getEfficiencyName()
                ));
    }

    @Override
    public List<EfficiencyDto> getAllEfficiencies() {
        List<Efficiency> efficiencies = efficiencyRepository.findAll();
        List<EfficiencyDto> efficiencyDtos = new ArrayList<>();
        for (Efficiency efficiency : efficiencies) {
            EfficiencyDto efficiencyDto = new EfficiencyDto(
                    efficiency.getEfficiencyId(),
                    efficiency.getEfficiencyName()
            );
            efficiencyDtos.add(efficiencyDto);
        }
        return efficiencyDtos;
    }

    @Override
    public Optional<EfficiencyDto> updateEfficiency(int id, EfficiencyDto efficiencyDto) {
        return efficiencyRepository.findById(id)
                .map(efficiency -> {
                    efficiency.setEfficiencyName(efficiencyDto.getEfficiencyName());
                    efficiency = efficiencyRepository.save(efficiency);
                    return new EfficiencyDto(efficiency.getEfficiencyId(), efficiency.getEfficiencyName());
                });
    }

    @Override
    public boolean deleteEfficiency(int id) {
        if (efficiencyRepository.existsById(id)) {
            efficiencyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
