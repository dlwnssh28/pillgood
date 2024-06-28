package com.pillgood.repository;

import com.pillgood.entity.DetailedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailedQuestionRepository extends JpaRepository<DetailedQuestion, Integer> {
}
