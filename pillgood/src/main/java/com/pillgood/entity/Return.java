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
@Table(name = "returns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "order_no", nullable = false)
    private String orderNo;

    @Column(name = "return_status")
    private String returnStatus;
}
