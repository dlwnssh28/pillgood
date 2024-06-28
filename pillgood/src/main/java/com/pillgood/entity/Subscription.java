package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private int subscriptionId;

    @Column(name = "member_unique_id", length = 36)
    private String memberUniqueId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "subscription_status", length = 50)
    private String subscriptionStatus;

    @Column(name = "payment_no", length = 50)
    private String paymentNo;

    @ManyToOne
    @JoinColumn(name = "member_unique_id", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "payment_no", insertable = false, updatable = false)
    private Payment payment;
    
    public Subscription(int subscriptionId, String memberUniqueId, LocalDateTime startDate, LocalDateTime endDate, String subscriptionStatus, String paymentNo) {
        this.subscriptionId = subscriptionId;
        this.memberUniqueId = memberUniqueId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subscriptionStatus = subscriptionStatus;
        this.paymentNo = paymentNo;
    }
}
