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
@Table(name = "point_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_detail_id")
    private int pointDetailId;

    @Column(name = "member_unique_id")
    private String memberUniqueId;
    
    @Column(name = "point_status_code")
    private String pointStatusCode;

    @Column(name = "points")
    private int points;

    @Column(name = "detail_history_id")
    private int detailHistoryId;

    @Column(name = "point_id")
    private int pointId;

    @ManyToOne
    @JoinColumn(name = "member_unique_id", referencedColumnName = "member_unique_id", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "point_id", referencedColumnName = "point_id", insertable = false, updatable = false)
    private Point point;
}
