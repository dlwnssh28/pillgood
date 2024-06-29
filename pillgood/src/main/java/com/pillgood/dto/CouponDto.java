package com.pillgood.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {

    private Integer couponId;
    private String couponName;
    private Integer discountAmount;
    private String discountDescription;
    private String couponStatus;
    private Integer validityPeriod;
}
