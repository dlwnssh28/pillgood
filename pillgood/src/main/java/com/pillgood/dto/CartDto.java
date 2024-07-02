package com.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
	private int cartNo;
    private String memberUniqueId;
    private int productId;
    private int productQuantity;
    private int price; // 추가
}
