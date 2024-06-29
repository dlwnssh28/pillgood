package com.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pillgood.dto.CartDto;
import com.pillgood.entity.Cart;
import com.pillgood.repository.CartRepository;

import lombok.RequiredArgsConstructor;

// Service 구현 클래스
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public CartDto createCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setMemberUniqueId(cartDto.getMemberUniqueId());
        cart.setProductId(cartDto.getProductId());
        cart.setProductQuantity(cartDto.getProductQuantity());
        cart = cartRepository.save(cart);
        cartDto.setCartNo(cart.getCartNo());
        return cartDto;
    }

    @Override
    public Optional<CartDto> getCartById(int id) {
        return cartRepository.findById(id)
                .map(cart -> new CartDto(
                        cart.getCartNo(),
                        cart.getMemberUniqueId(),
                        cart.getProductId(),
                        cart.getProductQuantity()
                ));
    }

    @Override
    public List<CartDto> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(cart -> new CartDto(
                        cart.getCartNo(),
                        cart.getMemberUniqueId(),
                        cart.getProductId(),
                        cart.getProductQuantity()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartDto> updateCart(int id, CartDto cartDto) {
        return cartRepository.findById(id)
                .map(cart -> {
                    cart.setMemberUniqueId(cartDto.getMemberUniqueId());
                    cart.setProductId(cartDto.getProductId());
                    cart.setProductQuantity(cartDto.getProductQuantity());
                    cart = cartRepository.save(cart);
                    return new CartDto(
                            cart.getCartNo(),
                            cart.getMemberUniqueId(),
                            cart.getProductId(),
                            cart.getProductQuantity()
                    );
                });
    }

    @Override
    public boolean deleteCart(int id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
