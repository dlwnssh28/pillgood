package com.pillgood.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.PointDetailDto;
import com.pillgood.service.PointDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pointDetail")
@RequiredArgsConstructor
public class PointDetailController {

    private final PointDetailService pointDetailService;

    @PutMapping("/update/{id}")
    public ResponseEntity<PointDetailDto> updatePointDetail(
            @PathVariable int id,
            @RequestBody PointDetailDto pointDetailDto) {
        Optional<PointDetailDto> updatedPointDetail = pointDetailService.updatePointDetail(id, pointDetailDto);
        return updatedPointDetail
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
