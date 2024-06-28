package com.pillgood.service;

import java.util.Optional;

import com.pillgood.dto.PointDetailDto;

public interface PointDetailService {
    Optional<PointDetailDto> updatePointDetail(int id, PointDetailDto pointDetailDto);
}
