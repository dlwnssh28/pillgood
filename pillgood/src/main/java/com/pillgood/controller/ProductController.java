package com.pillgood.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pillgood.dto.ProductDto;
import com.pillgood.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDTO) {
        System.out.println("----adding new product.");
        ProductDto createdProductDTO = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable int id, @RequestBody ProductDto updatedProductDTO) {
        Optional<ProductDto> productDTO = productService.updateProduct(id, updatedProductDTO);
        return productDTO
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/status")
    public boolean updateProductStatus(@PathVariable int id, @RequestBody Map<String, Boolean> status) {
        boolean active = status.get("active");
        return productService.setActive(id, active);
    }

}
