package com.pillgood.service;

import java.util.List;

import com.pillgood.dto.ReturnDto;

public interface ReturnService {
    List<ReturnDto> getAllReturns();
    ReturnDto getReturnById(int returnId); // 변경된 부분: 반환 타입을 ReturnDto로 변경
    ReturnDto createReturn(ReturnDto returnDto);
    ReturnDto updateReturn(int returnId, ReturnDto returnDto);
    void deleteReturn(int returnId);
}
