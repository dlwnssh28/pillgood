package com.pillgood.controller;

import com.pillgood.dto.CartDto;
import com.pillgood.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/create")
    public Optional<CartDto> createCart(@RequestBody CartDto cartDto) {
        return Optional.of(cartService.createCart(cartDto));
    }

    @GetMapping("/find/{id}")
    public Optional<CartDto> getCartById(@PathVariable int id) {
        return cartService.getCartById(id);
    }

    @GetMapping("/list")
    public List<CartDto> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/member/{memberId}")
    public List<CartDto> getCartByMemberId(@PathVariable String memberId) {
        return cartService.getCartByMemberId(memberId);
    }

    @PutMapping("/update/{id}")
    public Optional<CartDto> updateCart(@PathVariable int id, @RequestBody CartDto cartDto) {
        return cartService.updateCart(id, cartDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCart(@PathVariable int id) {
        boolean deleted = cartService.deleteCart(id);
        if (!deleted) {
            throw new RuntimeException("삭제할 Cart 찾을 수 없음");
        }
    }
}
