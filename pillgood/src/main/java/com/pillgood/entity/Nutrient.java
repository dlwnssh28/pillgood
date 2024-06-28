package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nutrients")
@Data //@Getter @Setter @AllArgsConstructor
@NoArgsConstructor
public class Nutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_id")
    private int nutrientId;

    @Column(name = "nutrient_name")
    private String nutrientName;

    @Column(name = "description")
    private String description;
    
    // ID를 받는 생성자 추가
    public Nutrient(int nutrientId) {
        this.nutrientId = nutrientId;
    }
}
