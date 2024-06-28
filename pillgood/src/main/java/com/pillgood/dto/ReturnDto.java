package com.pillgood.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReturnDto {
    private int returnId;
    private LocalDateTime requestDate;
    private LocalDateTime receivedDate;
    private LocalDateTime processedDate;
    private LocalDateTime cancelledDate;
    private String orderNo;
    private String returnStatus;
}
