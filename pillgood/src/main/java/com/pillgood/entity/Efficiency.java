package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "efficiencies")
@Data
@NoArgsConstructor
public class Efficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "efficiency_id")
    private int efficiencyId;

    @Column(name = "efficiency_name")
    private String efficiencyName;
    
    // ID를 받는 생성자 추가
    public Efficiency(int efficiencyId) {
        this.efficiencyId = efficiencyId;
    }
}