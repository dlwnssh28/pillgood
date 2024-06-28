package com.pillgood.dto;

import lombok.Data;

@Data
public class OrderDetailDto {
    private int orderDetailNo;
    private String orderNo;
    private int productId;
    private int quantity;
    private int amount;
}
