package com.pillgood.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nutrient_efficiencies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NutrientEfficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_efficiency_id")
    private int nutrientEfficiencyId;

    @ManyToOne
    @JoinColumn(name = "nutrient_id", referencedColumnName = "nutrient_id")
    private Nutrient nutrient;

    @ManyToOne
    @JoinColumn(name = "efficiency_id", referencedColumnName = "efficiency_id")
    private Efficiency efficiency;
}
