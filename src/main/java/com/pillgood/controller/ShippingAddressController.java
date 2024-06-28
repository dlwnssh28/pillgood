package com.pillgood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pillgood.dto.ShippingAddressDto;
import com.pillgood.service.ShippingAddressService;

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

    @PostMapping("/create")
    public ResponseEntity<ShippingAddressDto> createShippingAddress(@RequestBody ShippingAddressDto shippingAddressDTO) {
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
