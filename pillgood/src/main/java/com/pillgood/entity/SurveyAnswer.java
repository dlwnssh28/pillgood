package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "survey_answers")
@Data
@NoArgsConstructor
public class SurveyAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private SurveyQuestion question;

    @Column(name = "answer_content", columnDefinition = "TEXT", nullable = false)
    private String answerContent;

    @Column(name = "deficiency_id", nullable = false)
    private Integer deficiencyId;

    // 기타 필요한 메서드들 추가
}
