package com.pillgood.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pillgood.dto.ReturnDto;
import com.pillgood.entity.Return;
import com.pillgood.repository.ReturnRepository;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnRepository returnRepository;

    @Override
    public List<ReturnDto> getAllReturns() {
        return returnRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReturnDto getReturnById(int returnId) {
        Optional<Return> returnOpt = returnRepository.findById(returnId);
        return returnOpt.map(this::convertToDto)
                        .orElseThrow(() -> new NoSuchElementException("Return not found for id: " + returnId));
    }

    @Override
    public ReturnDto createReturn(ReturnDto returnDto) {
        Return returnEntity = convertToEntity(returnDto);
        returnRepository.save(returnEntity);
        return convertToDto(returnEntity);
    }

    @Override
    public ReturnDto updateReturn(int returnId, ReturnDto returnDto) {
        Optional<Return> returnOpt = returnRepository.findById(returnId);
        if (returnOpt.isPresent()) {
            Return returnEntity = returnOpt.get();
            updateEntityFromDto(returnEntity, returnDto);
            returnRepository.save(returnEntity);
            return convertToDto(returnEntity);
        }
        return null;
    }

    @Override
    public void deleteReturn(int returnId) {
        returnRepository.deleteById(returnId);
    }

    private ReturnDto convertToDto(Return returnEntity) {
        return new ReturnDto(
                returnEntity.getReturnId(),
                returnEntity.getRequestDate(),
                returnEntity.getReceivedDate(),
                returnEntity.getProcessedDate(),
                returnEntity.getCancelledDate(),
                returnEntity.getOrderNo(),
                returnEntity.getReturnStatus()
        );
    }

    private Return convertToEntity(ReturnDto returnDto) {
        return new Return(
                returnDto.getReturnId(),
                returnDto.getRequestDate(),
                returnDto.getReceivedDate(),
                returnDto.getProcessedDate(),
                returnDto.getCancelledDate(),
                returnDto.getOrderNo(),
                returnDto.getReturnStatus()
        );
    }

    private void updateEntityFromDto(Return returnEntity, ReturnDto returnDto) {
        returnEntity.setRequestDate(returnDto.getRequestDate());
        returnEntity.setReceivedDate(returnDto.getReceivedDate());
        returnEntity.setProcessedDate(returnDto.getProcessedDate());
        returnEntity.setCancelledDate(returnDto.getCancelledDate());
        returnEntity.setOrderNo(returnDto.getOrderNo());
        returnEntity.setReturnStatus(returnDto.getReturnStatus());
    }
}
