package com.pillgood.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detailed_questions")
@Data
public class DetailedQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailedQuestionId;

    private int deficiencyId;

    @Column(columnDefinition = "TEXT")
    private String questionContent;

    // Getter and Setter for detailedQuestionId
    public int getDetailedQuestionId() {
        return detailedQuestionId;
    }

    public void setDetailedQuestionId(int detailedQuestionId) {
        this.detailedQuestionId = detailedQuestionId;
    }

    // Getter and Setter for deficiencyId
    public int getDeficiencyId() {
        return deficiencyId;
    }

    public void setDeficiencyId(int deficiencyId) {
        this.deficiencyId = deficiencyId;
    }

    // Getter and Setter for questionContent
    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
