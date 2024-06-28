package com.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pillgood.dto.PointDto;
import com.pillgood.entity.Point;
import com.pillgood.repository.PointRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;

    @Override
    public List<PointDto> getAllPoints() {
        return pointRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PointDto> getPointById(int id) {
        return pointRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public PointDto createPoint(PointDto pointDto) {
        Point point = convertToEntity(pointDto);
        Point savedPoint = pointRepository.save(point);
        return convertToDTO(savedPoint);
    }

    @Override
    public Optional<PointDto> updatePoint(int id, PointDto updatedPointDto) {
        return pointRepository.findById(id)
                .map(point -> {
                    point.setMemberUniqueId(updatedPointDto.getMemberUniqueId());
                    point.setPointMasterId(updatedPointDto.getPointMasterId());
                    point.setPointStatusCode(updatedPointDto.getPointStatusCode());
                    point.setPoints(updatedPointDto.getPoints());
                    point.setTransactionDate(updatedPointDto.getTransactionDate());
                    point.setExpiryDate(updatedPointDto.getExpiryDate());
                    point.setReferenceId(updatedPointDto.getReferenceId());
                    Point updatedPoint = pointRepository.save(point);
                    return convertToDTO(updatedPoint);
                });
    }

    @Override
    public boolean deletePoint(int id) {
        if (pointRepository.existsById(id)) {
            pointRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PointDto convertToDTO(Point point) {
        return new PointDto(
                point.getPointId(),
                point.getMemberUniqueId(),
                point.getPointMasterId(),
                point.getPointStatusCode(),
                point.getPoints(),
                point.getTransactionDate(),
                point.getExpiryDate(),
                point.getReferenceId()
        );
    }

    private Point convertToEntity(PointDto pointDto) {
        return new Point(
                pointDto.getPointId(),
                pointDto.getMemberUniqueId(),
                pointDto.getPointMasterId(),
                pointDto.getPointStatusCode(),
                pointDto.getPoints(),
                pointDto.getTransactionDate(),
                pointDto.getExpiryDate(),
                pointDto.getReferenceId()
        );
    }
}
