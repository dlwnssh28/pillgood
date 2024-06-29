package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "notices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no", nullable = false)
    private Integer noticeNo;

    @Column(name = "notice_title", nullable = false, length = 255)
    private String noticeTitle;

    @Column(name = "notice_content", columnDefinition = "TEXT", nullable = false)
    private String noticeContent;
}
