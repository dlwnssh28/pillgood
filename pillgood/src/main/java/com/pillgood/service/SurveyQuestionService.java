package com.pillgood.service;

import com.pillgood.dto.SurveyQuestionDto;
import java.util.List;

public interface SurveyQuestionService {
    SurveyQuestionDto createQuestion(SurveyQuestionDto questionDto);
    List<SurveyQuestionDto> getAllQuestions();
    SurveyQuestionDto getQuestionById(int id);
    SurveyQuestionDto updateQuestion(int id, SurveyQuestionDto questionDto);
    boolean deleteQuestion(int id);
}
