package com.pillgood.service;

import java.util.List;
import java.util.Optional;

import com.pillgood.dto.PointDto;

public interface PointService {
    List<PointDto> getAllPoints();
    Optional<PointDto> getPointById(int id);
    PointDto createPoint(PointDto pointDto);
    Optional<PointDto> updatePoint(int id, PointDto updatedPointDto);
    boolean deletePoint(int id);
}
