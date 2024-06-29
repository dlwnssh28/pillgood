package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey_questions")
@Data
@NoArgsConstructor
public class SurveyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer id;

    @Column(name = "question_content", columnDefinition = "TEXT")
    private String questionContent;

    @ManyToOne
    @JoinColumn(name = "parent_question_id")
    private SurveyQuestion parentQuestion;

    // 기타 필요한 메서드들 추가
}
