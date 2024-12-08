package com.pillgood.service;

import com.pillgood.dto.SurveyDto;
import com.pillgood.entity.Survey;

import java.util.List;
import java.util.Optional;

public interface SurveyService {
    List<SurveyDto> getAllSurveys();
    Optional<SurveyDto> getSurveyById(int id);
    List<SurveyDto> getSurveysByMemberId(String memberUniqueId);
    SurveyDto createSurvey(SurveyDto surveyDto);
    Optional<SurveyDto> updateSurvey(int id, SurveyDto surveyDto);
    boolean deleteSurvey(int id);
    SurveyDto convertToDto(Survey surveyEntity);
    Survey convertToEntity(SurveyDto surveyDto);
}
