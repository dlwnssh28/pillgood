package com.pillgood.dto;

import lombok.Data;

@Data
public class SurveyQuestionDto {
    private Integer id;
    private String questionContent;
    private Integer parentQuestionId;
}
