package com.pillgood.service;

import com.pillgood.dto.ProductDto;
import com.pillgood.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto createProduct(ProductDto productDTO);
    Optional<ProductDto> updateProduct(int id, ProductDto updatedProductDTO);
    ProductDto convertToDTO(Product product);
    Product convertToEntity(ProductDto productDTO);

    boolean setActive(int id, boolean active);
    Optional<ProductDto> getProductById(int id);
}
