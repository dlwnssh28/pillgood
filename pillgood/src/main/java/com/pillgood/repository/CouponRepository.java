package com.pillgood.repository;

import com.pillgood.entity.Coupon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	
}
