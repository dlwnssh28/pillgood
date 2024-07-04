package com.pillgood.service;

import com.pillgood.dto.SurveyAnswerDto;
import com.pillgood.entity.SurveyAnswer;
import com.pillgood.entity.SurveyQuestion;
import com.pillgood.repository.SurveyAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyAnswerServiceImpl implements SurveyAnswerService {

    @Autowired
    private SurveyAnswerRepository surveyAnswerRepository;

    @Override
    public SurveyAnswerDto createAnswer(SurveyAnswerDto answerDto) {
        SurveyAnswer entity = convertToEntity(answerDto);
        return convertToDto(surveyAnswerRepository.save(entity));
    }

    @Override
    public List<SurveyAnswerDto> getAllAnswers() {
        return surveyAnswerRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SurveyAnswerDto getAnswerById(int id) {
        return surveyAnswerRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public SurveyAnswerDto updateAnswer(int id, SurveyAnswerDto answerDto) {
        return surveyAnswerRepository.findById(id)
                .map(existingAnswer -> {
                    SurveyQuestion questionEntity = new SurveyQuestion();
                    questionEntity.setId(answerDto.getQuestionId());
                    existingAnswer.setQuestion(questionEntity);
                    existingAnswer.setAnswerContent(answerDto.getAnswerContent());
                    existingAnswer.setDeficiencyId(answerDto.getDeficiencyId());
                    return convertToDto(surveyAnswerRepository.save(existingAnswer));
                }).orElse(null);
    }

    @Override
    public boolean deleteAnswer(int id) {
        if (surveyAnswerRepository.existsById(id)) {
            surveyAnswerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private SurveyAnswerDto convertToDto(SurveyAnswer entity) {
        SurveyAnswerDto dto = new SurveyAnswerDto();
        dto.setId(entity.getId());
        dto.setQuestionId(entity.getQuestion().getId());
        dto.setAnswerContent(entity.getAnswerContent());
        dto.setDeficiencyId(entity.getDeficiencyId());
        return dto;
    }

    private SurveyAnswer convertToEntity(SurveyAnswerDto dto) {
        SurveyAnswer entity = new SurveyAnswer();
        SurveyQuestion questionEntity = new SurveyQuestion();
        questionEntity.setId(dto.getQuestionId());
        entity.setQuestion(questionEntity);
        entity.setAnswerContent(dto.getAnswerContent());
        entity.setDeficiencyId(dto.getDeficiencyId());
        return entity;
    }
}
