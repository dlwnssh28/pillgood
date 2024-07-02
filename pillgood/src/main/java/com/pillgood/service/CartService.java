package com.pillgood.service;

import com.pillgood.dto.CartDto;

import java.util.List;
import java.util.Optional;

public interface CartService {
    CartDto createCart(CartDto cartDto);
    Optional<CartDto> getCartById(int id);
    List<CartDto> getAllCarts();
    List<CartDto> getCartByMemberId(String memberId);
    Optional<CartDto> updateCart(int id, CartDto cartDto);
    boolean deleteCart(int id);
}
