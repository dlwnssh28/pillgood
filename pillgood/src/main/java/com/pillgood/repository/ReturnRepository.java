package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pillgood.entity.Return;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Integer> {
}
