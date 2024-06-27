package kr.co.pillgood.service;

import java.util.List;
import java.util.Optional;

import kr.co.pillgood.dto.ShippingAddressDTO;
import kr.co.pillgood.entity.ShippingAddress;

public interface ShippingAddressService {
    List<ShippingAddressDTO> getAllShippingAddresses();
    ShippingAddressDTO createShippingAddress(ShippingAddressDTO shippingAddressDTO);
    Optional<ShippingAddressDTO> updateShippingAddress(int id, ShippingAddressDTO updatedShippingAddressDTO);
    boolean deleteShippingAddress(int id);
    ShippingAddressDTO convertToDTO(ShippingAddress shippingAddress);
    ShippingAddress convertToEntity(ShippingAddressDTO shippingAddressDTO);
}
