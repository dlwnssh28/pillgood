package com.pillgood.service;

import com.pillgood.dto.CartDto;
import com.pillgood.entity.Cart;
import com.pillgood.entity.Product;
import com.pillgood.repository.CartRepository;
import com.pillgood.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;

import com.pillgood.dto.CartDto;
import com.pillgood.entity.Cart;
import com.pillgood.repository.CartRepository;
import com.pillgood.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

>>>>>>> origin/hzz
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
<<<<<<< HEAD
    private final ProductRepository productRepository; // 추가

    @Override
    public CartDto createCart(CartDto cartDto) {
        Cart cart = convertToEntity(cartDto);
        cartRepository.save(cart);
        return convertToDto(cart);
=======
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
>>>>>>> origin/hzz
    }

    @Override
    public Optional<CartDto> getCartById(int id) {
<<<<<<< HEAD
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.map(this::convertToDto);
=======
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
>>>>>>> origin/hzz
    }

    @Override
    public List<CartDto> getAllCarts() {
        return cartRepository.findAll().stream()
<<<<<<< HEAD
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CartDto> getCartByMemberId(String memberId) {
        System.out.println(memberId + ": 상품 조회");
        return cartRepository.findByMemberUniqueId(memberId).stream()
                .map(this::convertToDto)
=======
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
>>>>>>> origin/hzz
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartDto> updateCart(int id, CartDto cartDto) {
<<<<<<< HEAD
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            Cart updatedCart = cart.get();
            updatedCart.setProductQuantity(cartDto.getProductQuantity());
            cartRepository.save(updatedCart);
            return Optional.of(convertToDto(updatedCart));
        }
        return Optional.empty();
=======
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
>>>>>>> origin/hzz
    }

    @Override
    public boolean deleteCart(int id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private CartDto convertToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCartNo(cart.getCartNo());
        cartDto.setMemberUniqueId(cart.getMemberUniqueId());
        cartDto.setProductId(cart.getProductId());
        cartDto.setProductQuantity(cart.getProductQuantity());
        
        // productId를 통해 products 테이블에서 price 정보를 가져옴
        Product product = productRepository.findById(cart.getProductId())
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다: " + cart.getProductId()));
        cartDto.setPrice(product.getPrice());
        
        return cartDto;
    }

    private Cart convertToEntity(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setCartNo(cartDto.getCartNo());
        cart.setMemberUniqueId(cartDto.getMemberUniqueId());
        cart.setProductId(cartDto.getProductId());
        cart.setProductQuantity(cartDto.getProductQuantity());
        return cart;
    }
}
