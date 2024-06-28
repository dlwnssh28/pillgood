package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Getter
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private int subscriptionId;

    @Column(name = "member_unique_id", insertable = false, updatable = false)
    private String memberUniqueId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "subscription_status")
    private String subscriptionStatus;

    @Column(name = "payment_no", insertable = false, updatable = false)
    private String paymentNo;

//    @ManyToOne
//    @JoinColumn(name = "member_unique_id", referencedColumnName = "member_unique_id")
//    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "payment_no", referencedColumnName = "payment_no")
    private Payment payment;
}