package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pillgood.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
