package com.pillgood.service;

import com.pillgood.dto.SurveyAnswerDto;
import java.util.List;

public interface SurveyAnswerService {
    SurveyAnswerDto createAnswer(SurveyAnswerDto answerDto);
    List<SurveyAnswerDto> getAllAnswers();
    SurveyAnswerDto getAnswerById(int id);
    SurveyAnswerDto updateAnswer(int id, SurveyAnswerDto answerDto);
    boolean deleteAnswer(int id);
}
