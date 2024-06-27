package kr.co.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.co.pillgood.entity.ShippingAddress;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
}
