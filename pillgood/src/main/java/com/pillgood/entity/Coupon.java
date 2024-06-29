package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "coupons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id", nullable = false)
    private Integer couponId;

    @Column(name = "coupon_name", length = 100, nullable = false)
    private String couponName;

    @Column(name = "discount_amount", nullable = false)
    private Integer discountAmount;

    @Column(name = "discount_description", length = 255, nullable = true)
    private String discountDescription;

    @Column(name = "coupon_status", length = 50, nullable = true)
    private String couponStatus;

    @Column(name = "validity_period", nullable = true)
    private Integer validityPeriod;
}
