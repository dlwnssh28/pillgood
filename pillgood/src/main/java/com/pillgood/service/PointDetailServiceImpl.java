package com.pillgood.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pillgood.dto.PointDetailDto;
import com.pillgood.entity.PointDetail;
import com.pillgood.repository.PointDetailRepository;

@Service
public class PointDetailServiceImpl implements PointDetailService {

    @Autowired
    private PointDetailRepository pointDetailRepository;

    @Override
    public Optional<PointDetailDto> updatePointDetail(int id, PointDetailDto pointDetailDto) {
        return pointDetailRepository.findById(id)
                .map(pointDetail -> {
                    pointDetail.setMemberUniqueId(pointDetailDto.getMemberUniqueId());
                    pointDetail.setPointStatusCode(pointDetailDto.getPointStatusCode());
                    pointDetail.setPoints(pointDetailDto.getPoints());
                    pointDetail.setDetailHistoryId(pointDetailDto.getDetailHistoryId());
                    pointDetail.setPointId(pointDetailDto.getPointId());
                    PointDetail updatedPointDetail = pointDetailRepository.save(pointDetail);
                    return convertToDTO(updatedPointDetail);
                });
    }

    private PointDetailDto convertToDTO(PointDetail pointDetail) {
        return new PointDetailDto(
                pointDetail.getPointDetailId(),
                pointDetail.getMemberUniqueId(),
                pointDetail.getPointStatusCode(),
                pointDetail.getPoints(),
                pointDetail.getDetailHistoryId(),
                pointDetail.getPointId()
        );
    }
}
