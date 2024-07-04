package com.pillgood.service;

import com.pillgood.dto.SurveyDto;
import com.pillgood.entity.Survey;
import com.pillgood.repository.MemberRepository;
import com.pillgood.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<SurveyDto> getAllSurveys() {
        return surveyRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SurveyDto> getSurveyById(int id) {
        return surveyRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    public List<SurveyDto> getSurveysByMemberId(String memberUniqueId) {
        return surveyRepository.findByMemberUniqueId(memberUniqueId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public SurveyDto createSurvey(SurveyDto surveyDto) {
//        // member_unique_id가 null이거나 해당 ID가 존재하지 않는 경우 예외를 던짐
//        if (surveyDto.getMemberUniqueId() == null || !memberRepository.existsById(surveyDto.getMemberUniqueId())) {
//            throw new IllegalArgumentException("Invalid member ID: " + surveyDto.getMemberUniqueId());
//        }
//
//        // 설문 엔티티로 변환
//        Survey surveyEntity = convertToEntity(surveyDto);
//
//        // 설문 엔티티를 데이터베이스에 저장
//        Survey savedSurvey = surveyRepository.save(surveyEntity);
//
//        // 저장된 설문 엔티티를 DTO로 변환하여 반환
//        return convertToDto(savedSurvey);
//    }
    
    @Override
    public SurveyDto createSurvey(SurveyDto surveyDto) {
        // member_unique_id가 null이거나 해당 ID가 존재하지 않는 경우 예외를 던짐
        if (surveyDto.getMemberUniqueId() == null || !memberRepository.existsById(surveyDto.getMemberUniqueId())) {
            System.out.println("Invalid member ID: " + surveyDto.getMemberUniqueId()); // 로그 추가
            throw new IllegalArgumentException("Invalid member ID: " + surveyDto.getMemberUniqueId());
        }

        // 설문 엔티티로 변환
        Survey surveyEntity = convertToEntity(surveyDto);

        // 설문 엔티티를 데이터베이스에 저장
        Survey savedSurvey = surveyRepository.save(surveyEntity);

        // 저장된 설문 엔티티를 DTO로 변환하여 반환
        return convertToDto(savedSurvey);
    }

    @Override
    public Optional<SurveyDto> updateSurvey(int id, SurveyDto surveyDto) {
        return surveyRepository.findById(id)
                .map(existingSurvey -> {
                    existingSurvey.setMemberUniqueId(surveyDto.getMemberUniqueId());
                    existingSurvey.setName(surveyDto.getName());
                    existingSurvey.setAge(surveyDto.getAge());
                    existingSurvey.setGender(surveyDto.getGender());
                    existingSurvey.setHeight(surveyDto.getHeight());
                    existingSurvey.setWeight(surveyDto.getWeight());
                    existingSurvey.setDeficiencyId1(surveyDto.getDeficiencyId1());
                    existingSurvey.setDeficiencyId2(surveyDto.getDeficiencyId2());
                    existingSurvey.setDeficiencyId3(surveyDto.getDeficiencyId3());
                    existingSurvey.setSurveyDate(surveyDto.getSurveyDate());
                    existingSurvey.setRecommendedProducts(surveyDto.getRecommendedProducts());
                    existingSurvey.setKeywords(surveyDto.getKeywords());
                    Survey updatedSurvey = surveyRepository.save(existingSurvey);
                    return convertToDto(updatedSurvey);
                });
    }

    @Override
    public boolean deleteSurvey(int id) {
        if (surveyRepository.existsById(id)) {
            surveyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public SurveyDto convertToDto(Survey surveyEntity) {
        return new SurveyDto(
                surveyEntity.getSurveyNo(), 
                surveyEntity.getMemberUniqueId(),
                surveyEntity.getName(),
                surveyEntity.getAge(),
                surveyEntity.getGender(),
                surveyEntity.getHeight(),
                surveyEntity.getWeight(),
                surveyEntity.getDeficiencyId1(),
                surveyEntity.getDeficiencyId2(),
                surveyEntity.getDeficiencyId3(),
                surveyEntity.getSurveyDate(),
                surveyEntity.getRecommendedProducts(),
                surveyEntity.getKeywords()
        );
    }

    @Override
    public Survey convertToEntity(SurveyDto surveyDto) {
        return new Survey(
                surveyDto.getSurveyNo(),
                surveyDto.getMemberUniqueId(),
                surveyDto.getName(),
                surveyDto.getAge(),
                surveyDto.getGender(),
                surveyDto.getHeight(),
                surveyDto.getWeight(),
                surveyDto.getDeficiencyId1(),
                surveyDto.getDeficiencyId2(),
                surveyDto.getDeficiencyId3(),
                surveyDto.getSurveyDate(),
                surveyDto.getRecommendedProducts(),
                surveyDto.getKeywords()
        );
    }
}
