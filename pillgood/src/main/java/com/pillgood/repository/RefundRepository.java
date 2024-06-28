package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pillgood.entity.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Integer> {
}
