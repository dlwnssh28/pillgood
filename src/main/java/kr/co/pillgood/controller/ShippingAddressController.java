package kr.co.pillgood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.pillgood.dto.ShippingAddressDTO;
import kr.co.pillgood.service.ShippingAddressService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shipping-addresses")
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    @GetMapping("/list")
    public List<ShippingAddressDTO> getAllShippingAddresses() {
        return shippingAddressService.getAllShippingAddresses();
    }

    @PostMapping("/create")
    public ResponseEntity<ShippingAddressDTO> createShippingAddress(@RequestBody ShippingAddressDTO shippingAddressDTO) {
        ShippingAddressDTO createdShippingAddressDTO = shippingAddressService.createShippingAddress(shippingAddressDTO);
        return new ResponseEntity<>(createdShippingAddressDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ShippingAddressDTO> updateShippingAddress(@PathVariable int id, @RequestBody ShippingAddressDTO updatedShippingAddressDTO) {
        Optional<ShippingAddressDTO> shippingAddressDTO = shippingAddressService.updateShippingAddress(id, updatedShippingAddressDTO);
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
