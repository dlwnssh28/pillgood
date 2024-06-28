package com.pillgood.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private String paymentNo;
    private String paymentMethod;
    private int paymentAmount;
    private String orderNo;
    private LocalDateTime paymentDate;
    private String paymentStatus;
    private boolean subscriptionPaymentStatus;
}
