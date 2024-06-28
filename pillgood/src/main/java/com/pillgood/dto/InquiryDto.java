package com.pillgood.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {
    private int inquiryNo;
    private String memberUniqueId;
    private LocalDateTime inquiryDate;
    private String inquiryStatus;
    private String inquiryType;
    private String inquiryTitle;
    private String inquiryContent;
}
