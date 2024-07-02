package com.pillgood.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_no")
    private int orderDetailNo;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "amount")
    private int amount;

}
