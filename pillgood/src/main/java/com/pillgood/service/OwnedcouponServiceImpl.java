package com.pillgood.service;

import com.pillgood.dto.CouponDto;
import com.pillgood.dto.OwnedcouponDto;
import com.pillgood.entity.Ownedcoupon;
import com.pillgood.repository.OwnedcouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnedcouponServiceImpl implements OwnedcouponService {

    private final OwnedcouponRepository ownedcouponRepository;

    @Override
    public List<OwnedcouponDto> getAllOwnedCoupons() {
        return ownedcouponRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<OwnedcouponDto> getOwnedCouponByMemberId(String memberId) {
        System.out.println(memberId + ": 쿠폰 조회");
        return ownedcouponRepository.findByMemberUniqueId(memberId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<OwnedcouponDto> getOwnedCouponsByMember(String memberUniqueId) {
        return ownedcouponRepository.findByMemberUniqueId(memberUniqueId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OwnedcouponDto> getOwnedCouponById(int ownedCouponId) {
        return ownedcouponRepository.findById(ownedCouponId)
                .map(this::convertToDto);
    }

    @Override
    public OwnedcouponDto createOwnedCoupon(OwnedcouponDto ownedcouponDto) {
        Ownedcoupon ownedcouponEntity = convertToEntity(ownedcouponDto);
        Ownedcoupon savedOwnedCoupon = ownedcouponRepository.save(ownedcouponEntity);
        return convertToDto(savedOwnedCoupon);
    }

    @Override
    public Optional<OwnedcouponDto> updateOwnedCoupon(int ownedCouponId, OwnedcouponDto updatedOwnedcouponDto) {
        return ownedcouponRepository.findById(ownedCouponId)
                .map(ownedcoupon -> {
                    ownedcoupon.setCouponId(updatedOwnedcouponDto.getCouponId());
                    ownedcoupon.setMemberUniqueId(updatedOwnedcouponDto.getMemberUniqueId());
                    ownedcoupon.setCouponUsed(updatedOwnedcouponDto.isCouponUsed());
                    ownedcoupon.setIssuedDate(updatedOwnedcouponDto.getIssuedDate());
                    ownedcoupon.setExpiryDate(updatedOwnedcouponDto.getExpiryDate());
                    Ownedcoupon updatedOwnedCoupon = ownedcouponRepository.save(ownedcoupon);
                    return convertToDto(updatedOwnedCoupon);
                });
    }

    @Override
    public boolean deleteOwnedCoupon(int ownedCouponId) {
        if (ownedcouponRepository.existsById(ownedCouponId)) {
            ownedcouponRepository.deleteById(ownedCouponId);
            return true;
        }
        return false;
    }

    @Override
    public OwnedcouponDto convertToDto(Ownedcoupon ownedcouponEntity) {
        return new OwnedcouponDto(
                ownedcouponEntity.getOwnedCouponId(),
                ownedcouponEntity.getCouponId(),
                ownedcouponEntity.getMemberUniqueId(),
                ownedcouponEntity.isCouponUsed(),
                ownedcouponEntity.getIssuedDate(),
                ownedcouponEntity.getExpiryDate()
        );
    }

    @Override
    public Ownedcoupon convertToEntity(OwnedcouponDto ownedcouponDto) {
        return new Ownedcoupon(
                ownedcouponDto.getOwnedCouponId(),
                ownedcouponDto.getCouponId(),
                ownedcouponDto.getMemberUniqueId(),
                ownedcouponDto.isCouponUsed(),
                ownedcouponDto.getIssuedDate(),
                ownedcouponDto.getExpiryDate()
        );
    }
}
