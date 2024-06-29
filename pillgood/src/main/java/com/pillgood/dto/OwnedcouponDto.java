package com.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnedcouponDto {
    private int ownedCouponId;
    private int couponId;
    private String memberUniqueId;
    private boolean couponUsed;
    private LocalDateTime issuedDate;
    private LocalDateTime expiryDate;
    
    public OwnedcouponDto(Integer ownedCouponId, Integer couponId, String memberUniqueId, boolean couponUsed, LocalDateTime issuedDate, LocalDateTime expiryDate) {
        this.ownedCouponId = ownedCouponId;
        this.couponId = couponId;
        this.memberUniqueId = memberUniqueId;
        this.couponUsed = couponUsed;
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
    }

}
