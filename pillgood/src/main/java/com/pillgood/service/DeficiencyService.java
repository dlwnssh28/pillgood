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

public interface DeficiencyService {
    DeficiencyDto createDeficiency(DeficiencyDto deficiencyDto);
    Optional<DeficiencyDto> getDeficiencyById(int id);
    List<DeficiencyDto> getAllDeficiencies();
    Optional<DeficiencyDto> updateDeficiency(int id, DeficiencyDto deficiencyDto);
    boolean deleteDeficiency(int id);
}

