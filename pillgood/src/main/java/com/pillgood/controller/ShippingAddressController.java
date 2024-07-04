package com.pillgood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pillgood.dto.CartDto;
import com.pillgood.dto.ShippingAddressDto;
import com.pillgood.service.ShippingAddressService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shipping-addresses")
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    @GetMapping("/list")
    public List<ShippingAddressDto> getAllShippingAddresses() {
        return shippingAddressService.getAllShippingAddresses();
    }
    
    @GetMapping("/findbyid")
    public ResponseEntity<?> getAddressesFindById(HttpSession session) {
        String memberId = (String) session.getAttribute("memberId");
        
        if (memberId == null) {
            return new ResponseEntity<>("세션에 memberId가 없습니다.", HttpStatus.UNAUTHORIZED);
        }
        
        System.out.println(memberId + ": 배송주소 조회");
        List<ShippingAddressDto> addresses = shippingAddressService.getAddressesByMemberId(memberId);
        
        if (addresses.isEmpty()) {
            return new ResponseEntity<>("장바구니에 항목이 없습니다.", HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ShippingAddressDto> createShippingAddress(HttpSession session, @RequestBody ShippingAddressDto shippingAddressDTO) {
    	String memberId = (String) session.getAttribute("memberId");
    	if (memberId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    	shippingAddressDTO.setMemberUniqueId(memberId);
        ShippingAddressDto createdShippingAddressDTO = shippingAddressService.createShippingAddress(shippingAddressDTO);        
        return new ResponseEntity<>(createdShippingAddressDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ShippingAddressDto> updateShippingAddress(@PathVariable int id, @RequestBody ShippingAddressDto updatedShippingAddressDTO) {
        Optional<ShippingAddressDto> shippingAddressDTO = shippingAddressService.updateShippingAddress(id, updatedShippingAddressDTO);
        return shippingAddressDTO
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShippingAddress(@PathVariable int id) {
        boolean deleted = shippingAddressService.deleteShippingAddress(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
