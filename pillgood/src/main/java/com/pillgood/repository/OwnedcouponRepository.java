package com.pillgood.repository;

import com.pillgood.entity.Coupon;
import com.pillgood.entity.Ownedcoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnedcouponRepository extends JpaRepository<Ownedcoupon, Integer> {
    List<Ownedcoupon> findByMemberUniqueId(String memberUniqueId);
}
