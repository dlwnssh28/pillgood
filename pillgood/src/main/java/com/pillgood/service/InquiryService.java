package com.pillgood.service;

import java.util.List;

import com.pillgood.dto.InquiryDto;

public interface InquiryService {
    List<InquiryDto> getAllInquiries();
    InquiryDto getInquiryById(int inquiryNo);
    InquiryDto createInquiry(InquiryDto inquiryDto);
    InquiryDto updateInquiry(int inquiryNo, InquiryDto inquiryDto);
    void deleteInquiry(int inquiryNo);
}
