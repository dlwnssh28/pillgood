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
public class ReturnDto {
    private int returnId;
    private LocalDateTime requestDate;
    private LocalDateTime receivedDate;
    private LocalDateTime processedDate;
    private LocalDateTime cancelledDate;
    private String orderNo;
    private String returnStatus;
}
