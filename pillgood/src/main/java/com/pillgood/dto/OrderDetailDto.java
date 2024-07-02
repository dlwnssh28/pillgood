package com.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

    private int orderDetailNo;
    private String orderNo;
    private int productId;
    private int quantity;
    private int amount;
}
