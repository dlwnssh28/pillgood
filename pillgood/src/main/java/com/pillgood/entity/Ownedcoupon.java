package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "owned_coupons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ownedcoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owned_coupon_id", nullable = false)
    private Integer ownedCouponId;

    @Column(name = "coupon_id", nullable = false)
    private Integer couponId;

    @Column(name = "member_unique_id", length = 36, nullable = false)
    private String memberUniqueId;

    @Column(name = "coupon_used", nullable = false)
    private boolean couponUsed;

    @Column(name = "issued_date", nullable = false)
    private LocalDateTime issuedDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;
}
