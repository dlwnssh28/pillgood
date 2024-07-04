package com.pillgood.service;

import java.util.List;
import java.util.Optional;

import com.pillgood.dto.CartDto;
import com.pillgood.dto.ShippingAddressDto;
import com.pillgood.entity.ShippingAddress;

public interface ShippingAddressService {
    List<ShippingAddressDto> getAllShippingAddresses();
    ShippingAddressDto createShippingAddress(ShippingAddressDto shippingAddressDTO);
    Optional<ShippingAddressDto> updateShippingAddress(int id, ShippingAddressDto updatedShippingAddressDTO);
    boolean deleteShippingAddress(int id);
    List<ShippingAddressDto> getAddressesByMemberId(String memberId);
    ShippingAddressDto convertToDTO(ShippingAddress shippingAddress);
    ShippingAddress convertToEntity(ShippingAddressDto shippingAddressDTO);
}
