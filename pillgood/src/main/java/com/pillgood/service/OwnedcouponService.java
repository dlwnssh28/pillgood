package com.pillgood.service;

import com.pillgood.dto.CouponDto;
import com.pillgood.dto.OwnedcouponDto;
import com.pillgood.entity.Ownedcoupon;

import java.util.List;
import java.util.Optional;

public interface OwnedcouponService {
    List<OwnedcouponDto> getAllOwnedCoupons();
    List<OwnedcouponDto> getOwnedCouponsByMember(String memberUniqueId);
    List<OwnedcouponDto> getOwnedCouponByMemberId(String memberId);
    Optional<OwnedcouponDto> getOwnedCouponById(int ownedCouponId);
    OwnedcouponDto createOwnedCoupon(OwnedcouponDto ownedcouponDto);
    Optional<OwnedcouponDto> updateOwnedCoupon(int ownedCouponId, OwnedcouponDto updatedOwnedcouponDto);
    boolean deleteOwnedCoupon(int ownedCouponId);
    OwnedcouponDto convertToDto(Ownedcoupon ownedcouponEntity);
    Ownedcoupon convertToEntity(OwnedcouponDto ownedcouponDto);
}
