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
public class PointDto {
    private int pointId;
    private String memberUniqueId;
    private String pointMasterId;
    private String pointStatusCode;
    private int points;
    private LocalDateTime transactionDate;
    private LocalDateTime expiryDate;
    private String referenceId;
}
