package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "member_unique_id", insertable = false, updatable = false)
    private String memberUniqueId;

    @Column(name = "order_no", insertable = false, updatable = false)
    private String orderNo;

    @Column(name = "review_date")
    private LocalDateTime reviewDate;

    @Column(name = "review_content", columnDefinition = "TEXT")
    private String reviewContent;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review_image")
    private String reviewImage;

//    @ManyToOne
//    @JoinColumn(name = "member_unique_id", referencedColumnName = "member_unique_id")
//    private MemberEntity member;
//
//    @ManyToOne
//    @JoinColumn(name = "order_no", referencedColumnName = "order_no")
//    private OrderEntity order;
}
