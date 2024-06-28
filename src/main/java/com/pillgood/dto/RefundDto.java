package com.pillgood.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
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