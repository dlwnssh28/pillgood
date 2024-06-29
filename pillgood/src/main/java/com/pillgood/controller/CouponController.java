package com.pillgood.controller;

import com.pillgood.dto.CouponDto;
import com.pillgood.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public ResponseEntity<List<CouponDto>> getAllCoupons() {
        List<CouponDto> coupons = couponService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<CouponDto> getCouponById(@PathVariable Integer couponId) {
        return couponService.getCouponById(couponId) 
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CouponDto> createCoupon(@RequestBody CouponDto couponDto) {
        CouponDto createdCoupon = couponService.createCoupon(couponDto);
        return ResponseEntity.ok(createdCoupon);
    }

    @PutMapping("/{couponId}")
    public ResponseEntity<CouponDto> updateCoupon(@PathVariable Integer couponId, @RequestBody CouponDto couponDto) {
        return couponService.updateCoupon(couponId, couponDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Integer couponId) {
        if (couponService.deleteCoupon(couponId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
