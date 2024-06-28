package com.pillgood.repository;

import com.pillgood.entity.Efficiency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EfficiencyRepository extends JpaRepository<Efficiency, Integer> {
}