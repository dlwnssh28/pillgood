package com.pillgood.repository;

import com.pillgood.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {
    List<Survey> findByMemberUniqueId(String memberUniqueId);
}
