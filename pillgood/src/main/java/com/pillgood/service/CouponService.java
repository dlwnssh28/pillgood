package com.pillgood.service;

import com.pillgood.dto.CartDto;
import com.pillgood.dto.CouponDto;
import com.pillgood.entity.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponService {
    List<CouponDto> getAllCoupons();
    CouponDto createCoupon(CouponDto couponDto);
    Optional<CouponDto> updateCoupon(int id, CouponDto updatedCouponDto);
    boolean deleteCoupon(int id);
    Optional<CouponDto> getCouponById(int id); // 추가된 메서드
    CouponDto convertToDto(Coupon couponEntity);
    Coupon convertToEntity(CouponDto couponDto);
}
