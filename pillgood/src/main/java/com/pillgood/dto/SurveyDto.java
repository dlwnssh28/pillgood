package com.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDto {
    private int surveyNo;
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
}
