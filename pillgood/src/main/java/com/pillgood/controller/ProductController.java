package com.pillgood.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @GetMapping("/view/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        Optional<ProductDto> productDTO = productService.getProductById(id);
        return productDTO
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDTO) {
        System.out.println("----adding new product.");
        ProductDto createdProductDTO = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
    }

//    @PostMapping("/create")
//    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDTO) {
//        System.out.println("----adding new product.");
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonProduct = objectMapper.writeValueAsString(productDTO);
//            System.out.println("C 입력 제품 정보: " + jsonProduct);
//        } catch (Exception e) {
//            System.out.println("Error converting productDTO to JSON: " + e.getMessage());
//        }
//        ProductDto createdProductDTO = productService.createProduct(productDTO);
//        return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable int id, @RequestBody ProductDto updatedProductDTO) {
        Optional<ProductDto> productDTO = productService.updateProduct(id, updatedProductDTO);
        return productDTO
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateProductStatus(@PathVariable int id, @RequestBody Map<String, Boolean> status) {
        boolean active = status.get("active");
        boolean result = productService.setActive(id, active);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 새로운 엔드포인트 추가
    @GetMapping("/detail/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        Optional<ProductDto> productDTO = productService.getProductById(id);
        return productDTO
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
