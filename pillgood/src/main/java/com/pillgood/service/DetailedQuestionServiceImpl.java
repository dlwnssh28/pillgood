package com.pillgood.service;

import com.pillgood.dto.DetailedQuestionDto;
import com.pillgood.entity.DetailedQuestion;
import com.pillgood.repository.DetailedQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailedQuestionServiceImpl implements DetailedQuestionService {

    @Autowired
    private final DetailedQuestionRepository detailedQuestionRepository;

    @Override
    public DetailedQuestionDto createDetailedQuestion(DetailedQuestionDto detailedQuestionDto) {
        DetailedQuestion detailedQuestion = convertToEntity(detailedQuestionDto);
        detailedQuestion = detailedQuestionRepository.save(detailedQuestion);
        return convertToDto(detailedQuestion);
    }

    @Override
    public Optional<DetailedQuestionDto> getDetailedQuestionById(int id) {
        return detailedQuestionRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    public List<DetailedQuestionDto> getAllDetailedQuestions() {
        return detailedQuestionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DetailedQuestionDto> updateDetailedQuestion(int id, DetailedQuestionDto detailedQuestionDto) {
        return detailedQuestionRepository.findById(id)
                .map(detailedQuestion -> {
                    detailedQuestion.setDeficiencyId(detailedQuestionDto.getDeficiencyId());
                    detailedQuestion.setQuestionContent(detailedQuestionDto.getQuestionContent());
                    detailedQuestionRepository.save(detailedQuestion);
                    return convertToDto(detailedQuestion);
                });
    }

    @Override
    public boolean deleteDetailedQuestion(int id) {
        if (detailedQuestionRepository.existsById(id)) {
            detailedQuestionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private DetailedQuestionDto convertToDto(DetailedQuestion detailedQuestion) {
        return new DetailedQuestionDto(
                detailedQuestion.getDetailedQuestionId(),
                detailedQuestion.getDeficiencyId(),
                detailedQuestion.getQuestionContent()
        );
    }

    private DetailedQuestion convertToEntity(DetailedQuestionDto detailedQuestionDto) {
        DetailedQuestion detailedQuestion = new DetailedQuestion();
        detailedQuestion.setDetailedQuestionId(detailedQuestionDto.getDetailedQuestionId());
        detailedQuestion.setDeficiencyId(detailedQuestionDto.getDeficiencyId());
        detailedQuestion.setQuestionContent(detailedQuestionDto.getQuestionContent());
        return detailedQuestion;
    }
}
