package com.pillgood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.NutrientDto;
import com.pillgood.service.NutrientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/nutrients")
@RequiredArgsConstructor
public class NutrientController {

    private final NutrientService nutrientService;

    @PostMapping("/create")
    public NutrientDto createNutrient(@RequestBody NutrientDto nutrientDTO) {
        System.out.println("영양제 생성 시도: " + nutrientDTO);
        NutrientDto createdNutrient = nutrientService.createNutrient(nutrientDTO);
        System.out.println("생성된 영양제: " + createdNutrient);
        return createdNutrient;
    }

    @GetMapping("/find/{id}")
    public Optional<NutrientDto> getNutrientById(@PathVariable int id) {
        return nutrientService.getNutrientById(id);
    }

    @GetMapping("/list")
    public List<NutrientDto> getAllNutrients() {
        return nutrientService.getAllNutrients();
    }

    @PutMapping("/update/{id}")
    public Optional<NutrientDto> updateNutrient(@PathVariable int id, @RequestBody NutrientDto nutrientDTO) {
        return nutrientService.updateNutrient(id, nutrientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteNutrient(@PathVariable int id) {
        boolean deleted = nutrientService.deleteNutrient(id);
        if (!deleted) {
            throw new RuntimeException("삭제할 Nutrient 찾을 수 없음");
        }
    }
}
