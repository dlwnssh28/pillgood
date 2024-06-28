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
public class ProductDto {
	
    private int productId;
    private int nutrientId;
    private String productName;
    private String productImage;
    private int price;
    private int stock;
    private LocalDateTime productRegistrationDate;
    private String target;
}