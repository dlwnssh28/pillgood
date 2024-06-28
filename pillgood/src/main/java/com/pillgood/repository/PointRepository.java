package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.Point;

public interface PointRepository extends JpaRepository<Point, Integer> {
}
