package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor
public class Payment {

    @Id
    @Column(name = "payment_no")
    private String paymentNo;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_amount")
    private int paymentAmount;

    @Column(name = "order_no", insertable = false, updatable = false)
    private String orderNo;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "subscription_payment_status")
    private boolean subscriptionPaymentStatus;

//    @ManyToOne
//    @JoinColumn(name = "order_no", referencedColumnName = "order_no")
//    private OrderEntity order;
}
