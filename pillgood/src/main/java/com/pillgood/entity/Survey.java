package com.pillgood.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "surveys")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int surveyNo;

    @Column(nullable = false)
    private String memberUniqueId;

    private String name;
    private int age;
    private String gender;
    private int height;
    private int weight;
    private int deficiencyId1;
    private int deficiencyId2;
    private int deficiencyId3;
    private LocalDateTime surveyDate;
    private String recommendedProducts;
    private String keywords;
    /*
    @ManyToOne
    @JoinColumn(name = "member_unique_id", insertable = false, updatable = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "deficiency_id1", insertable = false, updatable = false)
    private DeficiencyEntity deficiency1;

    @ManyToOne
    @JoinColumn(name = "deficiency_id2", insertable = false, updatable = false)
    private DeficiencyEntity deficiency2;

    @ManyToOne
    @JoinColumn(name = "deficiency_id3", insertable = false, updatable = false)
    private DeficiencyEntity deficiency3;
    */
    
    
}
