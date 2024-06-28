package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pillgood.entity.NutrientEfficiency;

@Repository
public interface NutrientEfficiencyRepository extends JpaRepository<NutrientEfficiency, Integer> {
}
