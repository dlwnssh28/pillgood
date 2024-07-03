package com.pillgood.service;

import com.pillgood.dto.CartDto;
import com.pillgood.dto.CouponDto;
import com.pillgood.entity.Coupon;
import com.pillgood.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public List<CouponDto> getAllCoupons() {
        return couponRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CouponDto createCoupon(CouponDto couponDto) {
        Coupon couponEntity = convertToEntity(couponDto);
        Coupon savedCoupon = couponRepository.save(couponEntity);
        return convertToDto(savedCoupon);
    }

    @Override
    public Optional<CouponDto> updateCoupon(int id, CouponDto updatedCouponDto) {
        return couponRepository.findById(id)
                .map(coupon -> {
                    coupon.setCouponName(updatedCouponDto.getCouponName());
                    coupon.setDiscountAmount(updatedCouponDto.getDiscountAmount());
                    coupon.setDiscountDescription(updatedCouponDto.getDiscountDescription());
                    coupon.setCouponStatus(updatedCouponDto.getCouponStatus());
                    coupon.setValidityPeriod(updatedCouponDto.getValidityPeriod());
                    Coupon updatedCoupon = couponRepository.save(coupon);
                    return convertToDto(updatedCoupon);
                });
    }

    @Override
    public boolean deleteCoupon(int id) {
        if (couponRepository.existsById(id)) {
            couponRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<CouponDto> getCouponById(int id) { // 구현된 메서드
        return couponRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public CouponDto convertToDto(Coupon couponEntity) {
        return new CouponDto(
                couponEntity.getCouponId(),
                couponEntity.getCouponName(),
                couponEntity.getDiscountAmount(),
                couponEntity.getDiscountDescription(),
                couponEntity.getCouponStatus(),
                couponEntity.getValidityPeriod()
        );
    }

    @Override
    public Coupon convertToEntity(CouponDto couponDto) {
        return new Coupon(
                couponDto.getCouponId(),
                couponDto.getCouponName(),
                couponDto.getDiscountAmount(),
                couponDto.getDiscountDescription(),
                couponDto.getCouponStatus(),
                couponDto.getValidityPeriod()
        );
    }
}
