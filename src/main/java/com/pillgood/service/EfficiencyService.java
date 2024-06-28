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

public interface EfficiencyService {
    EfficiencyDto createEfficiency(EfficiencyDto efficiencyDto);
    Optional<EfficiencyDto> getEfficiencyById(int id);
    List<EfficiencyDto> getAllEfficiencies();
    Optional<EfficiencyDto> updateEfficiency(int id, EfficiencyDto efficiencyDto);
    boolean deleteEfficiency(int id);
}
