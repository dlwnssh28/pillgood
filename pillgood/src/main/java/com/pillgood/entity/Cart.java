package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_no")
    private int cartNo;

    @Column(name = "member_unique_id")
    private String memberUniqueId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_quantity")
    private int productQuantity;

    @ManyToOne
    @JoinColumn(name = "member_unique_id", referencedColumnName = "member_unique_id", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private Product product;
}
