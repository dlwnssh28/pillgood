package com.pillgood.service;

import java.util.List;

import com.pillgood.dto.AnswerDto;

public interface AnswerService {
    List<AnswerDto> getAllAnswers();
    AnswerDto getAnswerById(int inquiryNo);
    AnswerDto createAnswer(AnswerDto answerDto);
    AnswerDto updateAnswer(int inquiryNo, AnswerDto answerDto);
    void deleteAnswer(int inquiryNo);
}
