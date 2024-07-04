package com.pillgood.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "nutrient_id", referencedColumnName = "nutrient_id")
    private Nutrient nutrient;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_image")
    private String productImage; //quill

    @Column(name = "price")
    private int price;

    @Column(name = "stock")
    private int stock;

    @Column(name = "product_registration_date")
    private LocalDateTime productRegistrationDate;

    @Column(name = "target")
    private String target;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean active;
}