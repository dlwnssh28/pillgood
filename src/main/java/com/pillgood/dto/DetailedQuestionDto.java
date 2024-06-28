package com.pillgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedQuestionDto {
    private int detailedQuestionId;
    private int deficiencyId;
    private String questionContent;
}
