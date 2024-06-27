package kr.co.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kr.co.pillgood.dto.ShippingAddressDTO;
import kr.co.pillgood.entity.ShippingAddress;
import kr.co.pillgood.repository.ShippingAddressRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    @Override
    public List<ShippingAddressDTO> getAllShippingAddresses() {
        return shippingAddressRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShippingAddressDTO createShippingAddress(ShippingAddressDTO shippingAddressDTO) {
        ShippingAddress shippingAddress = convertToEntity(shippingAddressDTO);
        ShippingAddress savedShippingAddress = shippingAddressRepository.save(shippingAddress);
        return convertToDTO(savedShippingAddress);
    }

    @Override
    public Optional<ShippingAddressDTO> updateShippingAddress(int id, ShippingAddressDTO updatedShippingAddressDTO) {
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
    public ShippingAddressDTO convertToDTO(ShippingAddress shippingAddress) {
        return new ShippingAddressDTO(
                shippingAddress.getShippingAddrId(),
                shippingAddress.getMemberUniqueId(),
                shippingAddress.getPostalCode(),
                shippingAddress.getAddress(),
                shippingAddress.getDetailedAddress()
        );
    }

    @Override
    public ShippingAddress convertToEntity(ShippingAddressDTO shippingAddressDTO) {
        // 적절한 생성자를 사용하여 엔티티 객체 생성
        return new ShippingAddress(
                shippingAddressDTO.getMemberUniqueId(),
                shippingAddressDTO.getPostalCode(),
                shippingAddressDTO.getAddress(),
                shippingAddressDTO.getDetailedAddress()
        );
    }
}
