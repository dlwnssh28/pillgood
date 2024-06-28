package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "refunds")
@Getter
@NoArgsConstructor
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refund_id")
    private int refundId;

    @Column(name = "refund_request_date")
    private LocalDateTime refundRequestDate;

    @Column(name = "refund_complete_date")
    private LocalDateTime refundCompleteDate;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_refund_amount")
    private int totalRefundAmount;

    @Column(name = "refund_method")
    private String refundMethod;

    @Column(name = "refund_bank")
    private String refundBank;

    @Column(name = "refund_account")
    private String refundAccount;

    @Column(name = "account_holder")
    private String accountHolder;

    @Column(name = "refund_status")
    private String refundStatus;

    @Column(name = "order_no")
    private String orderNo;

//    @ManyToOne
//    @JoinColumn(name = "order_no", referencedColumnName = "order_no")
//    private OrderEntity order;
}
