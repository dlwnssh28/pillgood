package com.pillgood.service;

import java.util.List;

import com.pillgood.dto.RefundDto;

public interface RefundService {
    List<RefundDto> getAllRefunds();
    RefundDto getRefundById(int refundId); // 변경된 부분: 반환 타입을 RefundDto로 변경
    RefundDto createRefund(RefundDto refundDto);
    RefundDto updateRefund(int refundId, RefundDto refundDto);
    void deleteRefund(int refundId);
}
