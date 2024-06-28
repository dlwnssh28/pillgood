package com.pillgood.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pillgood.dto.RefundDto;
import com.pillgood.entity.Refund;
import com.pillgood.repository.RefundRepository;

@Service
public class RefundServiceImpl implements RefundService {

    @Autowired
    private RefundRepository refundRepository;

    @Override
    public List<RefundDto> getAllRefunds() {
        return refundRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RefundDto getRefundById(int refundId) {
        Optional<Refund> refundOpt = refundRepository.findById(refundId);
        return refundOpt.map(this::convertToDto)
                        .orElseThrow(() -> new NoSuchElementException("Refund not found for id: " + refundId));
    }

    @Override
    public RefundDto createRefund(RefundDto refundDto) {
        Refund refundEntity = convertToEntity(refundDto);
        refundRepository.save(refundEntity);
        return convertToDto(refundEntity);
    }

    @Override
    public RefundDto updateRefund(int refundId, RefundDto refundDto) {
        Optional<Refund> refundOpt = refundRepository.findById(refundId);
        if (refundOpt.isPresent()) {
            Refund refundEntity = refundOpt.get();
            updateEntityFromDto(refundEntity, refundDto);
            refundRepository.save(refundEntity);
            return convertToDto(refundEntity);
        }
        return null;
    }

    @Override
    public void deleteRefund(int refundId) {
        refundRepository.deleteById(refundId);
    }

    private RefundDto convertToDto(Refund refundEntity) {
        return new RefundDto(
                refundEntity.getRefundId(),
                refundEntity.getRefundRequestDate(),
                refundEntity.getRefundCompleteDate(),
                refundEntity.getOrderDate(),
                refundEntity.getTotalRefundAmount(),
                refundEntity.getRefundMethod(),
                refundEntity.getRefundBank(),
                refundEntity.getRefundAccount(),
                refundEntity.getAccountHolder(),
                refundEntity.getRefundStatus(),
                refundEntity.getOrderNo()
        );
    }

    private Refund convertToEntity(RefundDto refundDto) {
        return new Refund(
                refundDto.getRefundId(),
                refundDto.getRefundRequestDate(),
                refundDto.getRefundCompleteDate(),
                refundDto.getOrderDate(),
                refundDto.getTotalRefundAmount(),
                refundDto.getRefundMethod(),
                refundDto.getRefundBank(),
                refundDto.getRefundAccount(),
                refundDto.getAccountHolder(),
                refundDto.getRefundStatus(),
                refundDto.getOrderNo()
        );
    }

    private void updateEntityFromDto(Refund refundEntity, RefundDto refundDto) {
        refundEntity.setRefundRequestDate(refundDto.getRefundRequestDate());
        refundEntity.setRefundCompleteDate(refundDto.getRefundCompleteDate());
        refundEntity.setOrderDate(refundDto.getOrderDate());
        refundEntity.setTotalRefundAmount(refundDto.getTotalRefundAmount());
        refundEntity.setRefundMethod(refundDto.getRefundMethod());
        refundEntity.setRefundBank(refundDto.getRefundBank());
        refundEntity.setRefundAccount(refundDto.getRefundAccount());
        refundEntity.setAccountHolder(refundDto.getAccountHolder());
        refundEntity.setRefundStatus(refundDto.getRefundStatus());
        refundEntity.setOrderNo(refundDto.getOrderNo());
    }
}
