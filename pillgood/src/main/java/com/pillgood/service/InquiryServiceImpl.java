package com.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pillgood.dto.InquiryDto;
import com.pillgood.entity.Inquiry;
import com.pillgood.repository.InquiryRepository;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    @Override
    public List<InquiryDto> getAllInquiries() {
        return inquiryRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public InquiryDto getInquiryById(int inquiryNo) {
        Optional<Inquiry> inquiryOpt = inquiryRepository.findById(inquiryNo);
        return inquiryOpt.map(this::convertToDto).orElse(null);
    }

    @Override
    public InquiryDto createInquiry(InquiryDto inquiryDto) {
        Inquiry inquiryEntity = convertToEntity(inquiryDto);
        inquiryRepository.save(inquiryEntity);
        return convertToDto(inquiryEntity);
    }

    @Override
    public InquiryDto updateInquiry(int inquiryNo, InquiryDto inquiryDto) {
        Optional<Inquiry> inquiryOpt = inquiryRepository.findById(inquiryNo);
        if (inquiryOpt.isPresent()) {
            Inquiry inquiryEntity = inquiryOpt.get();
            updateEntityFromDto(inquiryEntity, inquiryDto);
            inquiryRepository.save(inquiryEntity);
            return convertToDto(inquiryEntity);
        }
        return null;
    }

    @Override
    public void deleteInquiry(int inquiryNo) {
        inquiryRepository.deleteById(inquiryNo);
    }

    private InquiryDto convertToDto(Inquiry inquiryEntity) {
        return new InquiryDto(
                inquiryEntity.getInquiryNo(),
                inquiryEntity.getMemberUniqueId(),
                inquiryEntity.getInquiryDate(),
                inquiryEntity.getInquiryStatus(),
                inquiryEntity.getInquiryType(),
                inquiryEntity.getInquiryTitle(),
                inquiryEntity.getInquiryContent()
        );
    }

    private Inquiry convertToEntity(InquiryDto inquiryDto) {
        return new Inquiry(
                inquiryDto.getInquiryNo(),
                inquiryDto.getMemberUniqueId(),
                inquiryDto.getInquiryDate(),
                inquiryDto.getInquiryStatus(),
                inquiryDto.getInquiryType(),
                inquiryDto.getInquiryTitle(),
                inquiryDto.getInquiryContent()
        );
    }

    private void updateEntityFromDto(Inquiry inquiryEntity, InquiryDto inquiryDto) {
        inquiryEntity.setMemberUniqueId(inquiryDto.getMemberUniqueId());
        inquiryEntity.setInquiryDate(inquiryDto.getInquiryDate());
        inquiryEntity.setInquiryStatus(inquiryDto.getInquiryStatus());
        inquiryEntity.setInquiryType(inquiryDto.getInquiryType());
        inquiryEntity.setInquiryTitle(inquiryDto.getInquiryTitle());
        inquiryEntity.setInquiryContent(inquiryDto.getInquiryContent());
    }
}
