package com.pillgood.controller;

import com.pillgood.dto.DeficiencyNutrientDto;
import com.pillgood.service.DeficiencyNutrientService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deficiency-nutrients/admin")
@RequiredArgsConstructor
public class DeficiencyNutrientController {

    private final DeficiencyNutrientService deficiencyNutrientService;

    @GetMapping("/create")
    public String deficiencyNutrientForm(Model model) {
        model.addAttribute("deficiencyNutrientDto", new DeficiencyNutrientDto());
        return "deficiencyNutrientForm";
    }

    @PostMapping("/create")
    public Optional<DeficiencyNutrientDto> createDeficiencyNutrient(@RequestBody DeficiencyNutrientDto dto) {
        return deficiencyNutrientService.createDeficiencyNutrient(dto);
    }

    @GetMapping("/find/{id}")
    public Optional<DeficiencyNutrientDto> getDeficiencyNutrientById(@PathVariable int id) {
        return deficiencyNutrientService.getDeficiencyNutrientById(id);
    }

    @GetMapping("/list")
    public String getAllDeficiencyNutrients(Model model) {
        List<DeficiencyNutrientDto> deficiencyNutrients = deficiencyNutrientService.getAllDeficiencyNutrients();
        model.addAttribute("deficiencyNutrients", deficiencyNutrients);
        return "deficiencyNutrients";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDeficiencyNutrient(@PathVariable int id) {
        boolean deleted = deficiencyNutrientService.deleteDeficiencyNutrient(id);
        if (!deleted) {
            throw new RuntimeException("삭제할 DeficiencyNutrient 찾을 수 없음");
        }
    }
}
