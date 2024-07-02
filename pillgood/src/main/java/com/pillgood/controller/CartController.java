package com.pillgood.controller;

import com.pillgood.dto.CartDto;
import com.pillgood.service.CartService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    
    @PostMapping("/create")
    public ResponseEntity<CartDto> createCart(HttpSession session, @RequestBody CartDto cartDto) {
        String memberId = (String) session.getAttribute("memberId");
        if (memberId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        cartDto.setMemberUniqueId(memberId);
        CartDto createdCart = cartService.createCart(cartDto);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public Optional<CartDto> getCartById(@PathVariable int id) {
        return cartService.getCartById(id);
    }
    
    @GetMapping("/findbyid")
    public ResponseEntity<?> getCartsFindById(HttpSession session) {
        String memberId = (String) session.getAttribute("memberId");
        
        if (memberId == null) {
            return new ResponseEntity<>("세션에 memberId가 없습니다.", HttpStatus.UNAUTHORIZED);
        }
        
        System.out.println(memberId + ": 상품 조회");
        List<CartDto> carts = cartService.getCartByMemberId(memberId);
        
        if (carts.isEmpty()) {
            return new ResponseEntity<>("장바구니에 항목이 없습니다.", HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(carts, HttpStatus.OK);
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
