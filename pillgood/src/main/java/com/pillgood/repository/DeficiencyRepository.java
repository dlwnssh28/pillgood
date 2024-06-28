package com.pillgood.repository;

import com.pillgood.entity.Deficiency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeficiencyRepository extends JpaRepository<Deficiency, Integer> {
}
