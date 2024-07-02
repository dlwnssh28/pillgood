package com.pillgood.controller;

import com.pillgood.dto.EfficiencyDto;
import com.pillgood.service.EfficiencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/efficiencies/admin")
@RequiredArgsConstructor
public class EfficiencyController {

    private final EfficiencyService efficiencyService;

    @PostMapping("/create")
    public EfficiencyDto createEfficiency(@RequestBody EfficiencyDto efficiencyDto) {
        return efficiencyService.createEfficiency(efficiencyDto);
    }

    @GetMapping("/find/{id}")
    public Optional<EfficiencyDto> getEfficiencyById(@PathVariable int id) {
        return efficiencyService.getEfficiencyById(id);
    }

    @GetMapping("/list")
    public List<EfficiencyDto> getAllEfficiencies() {
        return efficiencyService.getAllEfficiencies();
    }

    @PutMapping("/update/{id}")
    public Optional<EfficiencyDto> updateEfficiency(@PathVariable int id, @RequestBody EfficiencyDto efficiencyDto) {
        return efficiencyService.updateEfficiency(id, efficiencyDto);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteEfficiency(@PathVariable int id) {
        return efficiencyService.deleteEfficiency(id);
    }
}
