package com.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pillgood.dto.AnswerDto;
import com.pillgood.dto.InquiryDto;
import com.pillgood.entity.Answer;
import com.pillgood.entity.Inquiry;
import com.pillgood.repository.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<AnswerDto> getAllAnswers() {
        return answerRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnswerDto getAnswerById(int inquiryNo) {
        Optional<Answer> answerOpt = answerRepository.findById(inquiryNo);
        return answerOpt.map(this::convertToDto).orElse(null);
    }

    @Override
    public AnswerDto createAnswer(AnswerDto answerDto) {
        Answer answerEntity = convertToEntity(answerDto);
        answerRepository.save(answerEntity);
        return convertToDto(answerEntity);
    }

    @Override
    public AnswerDto updateAnswer(int inquiryNo, AnswerDto answerDto) {
        Optional<Answer> answerOpt = answerRepository.findById(inquiryNo);
        if (answerOpt.isPresent()) {
            Answer answerEntity = answerOpt.get();
            updateEntityFromDto(answerEntity, answerDto);
            answerRepository.save(answerEntity);
            return convertToDto(answerEntity);
        }
        return null;
    }

    @Override
    public void deleteAnswer(int inquiryNo) {
        answerRepository.deleteById(inquiryNo);
    }

    private AnswerDto convertToDto(Answer answerEntity) {
        return new AnswerDto(
                answerEntity.getInquiryNo(),
                answerEntity.getAnswerContent(),
                answerEntity.getAnswerDate(),
                new InquiryDto(
                        answerEntity.getInquiry().getInquiryNo(),
                        answerEntity.getInquiry().getMemberUniqueId(),
                        answerEntity.getInquiry().getInquiryDate(),
                        answerEntity.getInquiry().getInquiryStatus(),
                        answerEntity.getInquiry().getInquiryType(),
                        answerEntity.getInquiry().getInquiryTitle(),
                        answerEntity.getInquiry().getInquiryContent()
                )
        );
    }

    private Answer convertToEntity(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setInquiryNo(answerDto.getInquiryNo());
        answer.setAnswerContent(answerDto.getAnswerContent());
        answer.setAnswerDate(answerDto.getAnswerDate());
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryNo(answerDto.getInquiry().getInquiryNo());
        inquiry.setMemberUniqueId(answerDto.getInquiry().getMemberUniqueId());
        inquiry.setInquiryDate(answerDto.getInquiry().getInquiryDate());
        inquiry.setInquiryStatus(answerDto.getInquiry().getInquiryStatus());
        inquiry.setInquiryType(answerDto.getInquiry().getInquiryType());
        inquiry.setInquiryTitle(answerDto.getInquiry().getInquiryTitle());
        inquiry.setInquiryContent(answerDto.getInquiry().getInquiryContent());
        answer.setInquiry(inquiry);
        return answer;
    }

    private void updateEntityFromDto(Answer answerEntity, AnswerDto answerDto) {
        answerEntity.setAnswerContent(answerDto.getAnswerContent());
        answerEntity.setAnswerDate(answerDto.getAnswerDate());
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryNo(answerDto.getInquiry().getInquiryNo());
        inquiry.setMemberUniqueId(answerDto.getInquiry().getMemberUniqueId());
        inquiry.setInquiryDate(answerDto.getInquiry().getInquiryDate());
        inquiry.setInquiryStatus(answerDto.getInquiry().getInquiryStatus());
        inquiry.setInquiryType(answerDto.getInquiry().getInquiryType());
        inquiry.setInquiryTitle(answerDto.getInquiry().getInquiryTitle());
        inquiry.setInquiryContent(answerDto.getInquiry().getInquiryContent());
        answerEntity.setInquiry(inquiry);
    }
}
