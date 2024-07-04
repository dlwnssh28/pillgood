package com.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pillgood.dto.CartDto;
import com.pillgood.dto.ShippingAddressDto;
import com.pillgood.entity.ShippingAddress;
import com.pillgood.repository.ShippingAddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    @Override
    public List<ShippingAddressDto> getAllShippingAddresses() {
        return shippingAddressRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShippingAddressDto> getAddressesByMemberId(String memberId) {
        System.out.println(memberId + ": 배송주소 조회");
        return shippingAddressRepository.findByMemberUniqueId(memberId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShippingAddressDto createShippingAddress(ShippingAddressDto shippingAddressDTO) {
        ShippingAddress shippingAddress = convertToEntity(shippingAddressDTO);
        ShippingAddress savedShippingAddress = shippingAddressRepository.save(shippingAddress);
        return convertToDTO(savedShippingAddress);
    }

    @Override
    public Optional<ShippingAddressDto> updateShippingAddress(int id, ShippingAddressDto updatedShippingAddressDTO) {
        return shippingAddressRepository.findById(id)
                .map(shippingAddress -> {
                    shippingAddress.setMemberUniqueId(updatedShippingAddressDTO.getMemberUniqueId());
                    shippingAddress.setPostalCode(updatedShippingAddressDTO.getPostalCode());
                    shippingAddress.setAddress(updatedShippingAddressDTO.getAddress());
                    shippingAddress.setDetailedAddress(updatedShippingAddressDTO.getDetailedAddress());
                    ShippingAddress updatedShippingAddress = shippingAddressRepository.save(shippingAddress);
                    return convertToDTO(updatedShippingAddress);
                });
    }

    @Override
    public boolean deleteShippingAddress(int id) {
        if (shippingAddressRepository.existsById(id)) {
            shippingAddressRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ShippingAddressDto convertToDTO(ShippingAddress shippingAddress) {
        return new ShippingAddressDto(
                shippingAddress.getShippingAddrId(),
                shippingAddress.getMemberUniqueId(),
                shippingAddress.getPostalCode(),
                shippingAddress.getAddress(),
                shippingAddress.getDetailedAddress()
        );
    }

    @Override
    public ShippingAddress convertToEntity(ShippingAddressDto shippingAddressDTO) {
        // 적절한 생성자를 사용하여 엔티티 객체 생성
        return new ShippingAddress(
                shippingAddressDTO.getMemberUniqueId(),
                shippingAddressDTO.getPostalCode(),
                shippingAddressDTO.getAddress(),
                shippingAddressDTO.getDetailedAddress()
        );
    }
}
