package com.pillgood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.pillgood.dto.ProductDto;
import com.pillgood.entity.Product;
import com.pillgood.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto createProduct(ProductDto productDTO);
    Optional<ProductDto> updateProduct(int id, ProductDto updatedProductDTO);
    boolean deleteProduct(int id);
    ProductDto convertToDTO(Product product);
    Product convertToEntity(ProductDto productDTO);
}
