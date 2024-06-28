package com.pillgood.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SubscriptionDto {
    private int subscriptionId;
    private String memberUniqueId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String subscriptionStatus;
    private String paymentNo;
}
