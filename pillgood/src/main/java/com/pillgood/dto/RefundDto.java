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
public class RefundDto {
    private int refundId;
    private LocalDateTime refundRequestDate;
    private LocalDateTime refundCompleteDate;
    private LocalDateTime orderDate;
    private int totalRefundAmount;
    private String refundMethod;
    private String refundBank;
    private String refundAccount;
    private String accountHolder;
    private String refundStatus;
    private String orderNo;
}
