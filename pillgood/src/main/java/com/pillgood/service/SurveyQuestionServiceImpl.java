package com.pillgood.service;

import com.pillgood.dto.SurveyQuestionDto;
import com.pillgood.entity.SurveyQuestion;
import com.pillgood.repository.SurveyQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService {

    @Autowired
    private SurveyQuestionRepository surveyQuestionRepository;

    @Override
    public SurveyQuestionDto createQuestion(SurveyQuestionDto questionDto) {
        SurveyQuestion entity = convertToEntity(questionDto);
        return convertToDto(surveyQuestionRepository.save(entity));
    }

    @Override
    public List<SurveyQuestionDto> getAllQuestions() {
        return surveyQuestionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SurveyQuestionDto getQuestionById(int id) {
        return surveyQuestionRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public SurveyQuestionDto updateQuestion(int id, SurveyQuestionDto questionDto) {
        return surveyQuestionRepository.findById(id)
                .map(existingQuestion -> {
                    existingQuestion.setQuestionContent(questionDto.getQuestionContent());
                    if (questionDto.getParentQuestionId() != null) {
                        SurveyQuestion parentQuestion = new SurveyQuestion();
                        parentQuestion.setId(questionDto.getParentQuestionId());
                        existingQuestion.setParentQuestion(parentQuestion);
                    } else {
                        existingQuestion.setParentQuestion(null);
                    }
                    return convertToDto(surveyQuestionRepository.save(existingQuestion));
                }).orElse(null);
    }

    @Override
    public boolean deleteQuestion(int id) {
        if (surveyQuestionRepository.existsById(id)) {
            surveyQuestionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private SurveyQuestionDto convertToDto(SurveyQuestion entity) {
        SurveyQuestionDto dto = new SurveyQuestionDto();
        dto.setId(entity.getId());
        dto.setQuestionContent(entity.getQuestionContent());
        if (entity.getParentQuestion() != null) {
            dto.setParentQuestionId(entity.getParentQuestion().getId());
        }
        return dto;
    }

    private SurveyQuestion convertToEntity(SurveyQuestionDto dto) {
        SurveyQuestion entity = new SurveyQuestion();
        entity.setQuestionContent(dto.getQuestionContent());
        if (dto.getParentQuestionId() != null) {
            SurveyQuestion parentQuestion = new SurveyQuestion();
            parentQuestion.setId(dto.getParentQuestionId());
            entity.setParentQuestion(parentQuestion);
        }
        return entity;
    }
}
