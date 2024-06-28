package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pillgood.entity.PointDetail;

@Repository
public interface PointDetailRepository extends JpaRepository<PointDetail, Integer> {
}
