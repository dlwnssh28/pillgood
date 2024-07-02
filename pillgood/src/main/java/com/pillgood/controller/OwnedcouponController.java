package com.pillgood.controller;

import com.pillgood.dto.OwnedcouponDto;
import com.pillgood.service.OwnedcouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/ownedcoupons")
@RequiredArgsConstructor
public class OwnedcouponController {

    private final OwnedcouponService ownedcouponService;

    @GetMapping("/list")
    public ResponseEntity<List<OwnedcouponDto>> getAllOwnedCoupons() {
        List<OwnedcouponDto> ownedcoupons = ownedcouponService.getAllOwnedCoupons();
        return ResponseEntity.ok(ownedcoupons);
    }

    @GetMapping("/{ownedCouponId}")
    public ResponseEntity<OwnedcouponDto> getOwnedCouponById(@PathVariable Integer ownedCouponId) {
        return ownedcouponService.getOwnedCouponById(ownedCouponId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/member/{memberUniqueId}")
    public ResponseEntity<List<OwnedcouponDto>> getOwnedCouponsByMember(@PathVariable String memberUniqueId) {
        List<OwnedcouponDto> ownedcoupons = ownedcouponService.getOwnedCouponsByMember(memberUniqueId);
        return ResponseEntity.ok(ownedcoupons);
    }

    @PostMapping("/create")
    public ResponseEntity<OwnedcouponDto> createOwnedCoupon(@RequestBody OwnedcouponDto ownedcouponDto) {
        OwnedcouponDto createdOwnedCoupon = ownedcouponService.createOwnedCoupon(ownedcouponDto);
        return ResponseEntity.ok(createdOwnedCoupon);
    }

    @PutMapping("/update/{ownedCouponId}")
    public ResponseEntity<OwnedcouponDto> updateOwnedCoupon(@PathVariable Integer ownedCouponId, @RequestBody OwnedcouponDto ownedcouponDto) {
        return ownedcouponService.updateOwnedCoupon(ownedCouponId, ownedcouponDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{ownedCouponId}")
    public ResponseEntity<Void> deleteOwnedCoupon(@PathVariable Integer ownedCouponId) {
        if (ownedcouponService.deleteOwnedCoupon(ownedCouponId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
