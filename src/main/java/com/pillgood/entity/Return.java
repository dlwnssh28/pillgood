package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "returns")
@Getter
@NoArgsConstructor
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_id")
    private int returnId;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "received_date")
    private LocalDateTime receivedDate;

    @Column(name = "processed_date")
    private LocalDateTime processedDate;

    @Column(name = "cancelled_date")
    private LocalDateTime cancelledDate;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "return_status")
    private String returnStatus;
}
