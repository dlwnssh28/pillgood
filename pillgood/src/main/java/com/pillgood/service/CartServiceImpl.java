package com.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pillgood.dto.CartDto;
import com.pillgood.entity.Cart;
import com.pillgood.repository.CartRepository;
import com.pillgood.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public CartDto createCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setMemberUniqueId(cartDto.getMemberUniqueId());
        cart.setProductId(cartDto.getProductId());
        cart.setProductQuantity(cartDto.getProductQuantity());
        cart = cartRepository.save(cart);

        // Product의 price 가져오기
        int price = productRepository.findById(cartDto.getProductId())
                .map(product -> product.getPrice())
                .orElse(0);

        return new CartDto(
                cart.getCartNo(),
                cart.getMemberUniqueId(),
                cart.getProductId(),
                cart.getProductQuantity(),
                price
        );
    }

    @Override
    public Optional<CartDto> getCartById(int id) {
        return cartRepository.findById(id)
                .map(cart -> {
                    // Product의 price 가져오기
                    int price = productRepository.findById(cart.getProductId())
                            .map(product -> product.getPrice())
                            .orElse(0);

                    return new CartDto(
                            cart.getCartNo(),
                            cart.getMemberUniqueId(),
                            cart.getProductId(),
                            cart.getProductQuantity(),
                            price
                    );
                });
    }

    @Override
    public List<CartDto> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(cart -> {
                    // Product의 price 가져오기
                    int price = productRepository.findById(cart.getProductId())
                            .map(product -> product.getPrice())
                            .orElse(0);

                    return new CartDto(
                            cart.getCartNo(),
                            cart.getMemberUniqueId(),
                            cart.getProductId(),
                            cart.getProductQuantity(),
                            price
                    );
                })
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

                    // Product의 price 가져오기
                    int price = productRepository.findById(cart.getProductId())
                            .map(product -> product.getPrice())
                            .orElse(0);

                    return new CartDto(
                            cart.getCartNo(),
                            cart.getMemberUniqueId(),
                            cart.getProductId(),
                            cart.getProductQuantity(),
                            price
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
