package com.pillgood.dto;

import java.time.LocalDateTime;

import com.pillgood.entity.Product;
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
    private boolean active;

    // 엔티티를 받아서 초기화하는 생성자 추가
    public ProductDto(Product product) {
        this.productId = product.getProductId();
        this.nutrientId = product.getNutrient().getNutrientId();
        this.productName = product.getProductName();
        this.productImage = product.getProductImage();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.productRegistrationDate = product.getProductRegistrationDate();
        this.target = product.getTarget();
        this.active = product.isActive();
    }
}
