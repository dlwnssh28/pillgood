package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @Column(name = "order_no", length = 50)
    private String orderNo;

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "order_request", length = 255)
    private String orderRequest;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "recipient", length = 100)
    private String recipient;

    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "detailed_address", length = 255)
    private String detailedAddress;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "member_unique_id", length = 32)
    private String memberUniqueId;

    @Column(name = "owned_coupon_id")
    private int ownedCouponId;

    @Column(name = "order_status", length = 50)
    private String orderStatus;

    @Column(name = "subscription_status")
    private boolean subscriptionStatus;

    @ManyToOne
    @JoinColumn(name = "member_unique_id", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "owned_coupon_id", insertable = false, updatable = false)
    private Ownedcoupon ownedCoupon;
}
