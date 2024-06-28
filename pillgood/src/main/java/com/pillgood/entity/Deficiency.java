package com.pillgood.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "deficiencies")
public class Deficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deficiency_id")
    private int deficiencyId;

    @Column(name = "deficiency_name")
    private String deficiencyName;

    // Getters and Setters
    public int getDeficiencyId() {
        return deficiencyId;
    }

    public void setDeficiencyId(int deficiencyId) {
        this.deficiencyId = deficiencyId;
    }

    public String getDeficiencyName() {
        return deficiencyName;
    }

    public void setDeficiencyName(String deficiencyName) {
        this.deficiencyName = deficiencyName;
    }
}
