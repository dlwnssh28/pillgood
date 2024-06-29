package com.pillgood.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carts")
@Getter //읽기 전용 접근자 제공
@Setter
@NoArgsConstructor
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
