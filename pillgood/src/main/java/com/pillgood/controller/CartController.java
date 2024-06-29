package com.pillgood.controller;

import com.pillgood.dto.CartDto;
import com.pillgood.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/create")
    public String cartForm(Model model) {
        model.addAttribute("cartDTO", new CartDto());
        return "cartForm";
    }

    @PostMapping("/create")
    public Optional<CartDto> createCart(@RequestBody CartDto cartDto) {
        return Optional.of(cartService.createCart(cartDto));
    }

    @GetMapping("/find/{id}")
    public Optional<CartDto> getCartById(@PathVariable int id) {
        return cartService.getCartById(id);
    }

    @GetMapping("/list")
    public String getAllCarts(Model model) {
        List<CartDto> carts = cartService.getAllCarts();
        model.addAttribute("carts", carts);
        return "cartList";
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
