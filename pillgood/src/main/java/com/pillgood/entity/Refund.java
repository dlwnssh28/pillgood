package com.pillgood.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "refunds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "refund_method", length = 50)
    private String refundMethod;

    @Column(name = "refund_bank", length = 50)
    private String refundBank;

    @Column(name = "refund_account", length = 50)
    private String refundAccount;

    @Column(name = "account_holder", length = 50)
    private String accountHolder;

    @Column(name = "refund_status", length = 50)
    private String refundStatus;

    @Column(name = "order_no", length = 50, nullable = false)
    private String orderNo;
}
