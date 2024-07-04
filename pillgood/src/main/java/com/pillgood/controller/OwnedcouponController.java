package com.pillgood.controller;

import com.pillgood.dto.CouponDto;
import com.pillgood.dto.OwnedcouponDto;
import com.pillgood.service.OwnedcouponService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ownedcoupons")
@RequiredArgsConstructor
public class OwnedcouponController {

    private final OwnedcouponService ownedcouponService;

    @GetMapping("/list")
    public ResponseEntity<List<OwnedcouponDto>> getAllOwnedCoupons() {
        List<OwnedcouponDto> ownedcoupons = ownedcouponService.getAllOwnedCoupons();
        return ResponseEntity.ok(ownedcoupons);
    }

    
    @GetMapping("/findbyid")
    public ResponseEntity<?> getCouponsFindById(HttpSession session) {
        String memberId = (String) session.getAttribute("memberId");
        
        if (memberId == null) {
            return new ResponseEntity<>("세션에 memberId가 없습니다.", HttpStatus.UNAUTHORIZED);
        }
        
        System.out.println(memberId + ": 쿠폰 조회");
        List<OwnedcouponDto> ownedcoupons = ownedcouponService.getOwnedCouponByMemberId(memberId);
        
        if (ownedcoupons.isEmpty()) {
            return new ResponseEntity<>("보유 쿠폰이 없습니다.", HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(ownedcoupons, HttpStatus.OK);
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
