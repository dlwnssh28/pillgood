package com.pillgood.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inquiries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_no")
    private int inquiryNo;

    @Column(name = "member_unique_id", length = 36)
    private String memberUniqueId;

    @Column(name = "inquiry_date")
    private LocalDateTime inquiryDate;

    @Column(name = "inquiry_status", length = 50)
    private String inquiryStatus;

    @Column(name = "inquiry_type", length = 50)
    private String inquiryType;

    @Column(name = "inquiry_title", length = 255)
    private String inquiryTitle;

    @Column(name = "inquiry_content", columnDefinition = "TEXT")
    private String inquiryContent;
}
