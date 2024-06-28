package com.pillgood.service;

import com.pillgood.dto.DetailedQuestionDto;

import java.util.List;
import java.util.Optional;

public interface DetailedQuestionService {
    DetailedQuestionDto createDetailedQuestion(DetailedQuestionDto detailedQuestionDto);
    Optional<DetailedQuestionDto> getDetailedQuestionById(int id);
    List<DetailedQuestionDto> getAllDetailedQuestions();
    Optional<DetailedQuestionDto> updateDetailedQuestion(int id, DetailedQuestionDto detailedQuestionDto);
    boolean deleteDetailedQuestion(int id);
}
