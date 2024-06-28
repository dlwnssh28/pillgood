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
@Table(name = "points")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private int pointId;

    @Column(name = "member_unique_id")
    private String memberUniqueId;

    @Column(name = "point_master_id")
    private String pointMasterId;

    @Column(name = "point_status_code")
    private String pointStatusCode;

    @Column(name = "points")
    private int points;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "reference_id")
    private String referenceId;
}
