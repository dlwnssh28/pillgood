package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "deficiency_nutrients")
@Data
public class DeficiencyNutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deficiency_nutrient_id")
    private int deficiencyNutrientId;

    @ManyToOne
    @JoinColumn(name = "deficiency_id", nullable = false)
    private Deficiency deficiency;

    @ManyToOne
    @JoinColumn(name = "nutrient_id", nullable = false)
    private Nutrient nutrient;
}
