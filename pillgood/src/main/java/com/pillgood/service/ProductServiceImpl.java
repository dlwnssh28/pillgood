package com.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pillgood.dto.ProductDto;
import com.pillgood.entity.Product;
import com.pillgood.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> getProductById(int id) {
        return productRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ProductDto createProduct(ProductDto productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    @Override
    public Optional<ProductDto> updateProduct(int id, ProductDto updatedProductDTO) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setNutrientId(updatedProductDTO.getNutrientId());
                    product.setProductName(updatedProductDTO.getProductName());
                    product.setProductImage(updatedProductDTO.getProductImage());
                    product.setPrice(updatedProductDTO.getPrice());
                    product.setStock(updatedProductDTO.getStock());
                    product.setProductRegistrationDate(updatedProductDTO.getProductRegistrationDate());
                    product.setTarget(updatedProductDTO.getTarget());
                    Product updatedProduct = productRepository.save(product);
                    return convertToDTO(updatedProduct);
                });
    }

    @Override
    public boolean deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ProductDto convertToDTO(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getNutrientId(),
                product.getProductName(),
                product.getProductImage(),
                product.getPrice(),
                product.getStock(),
                product.getProductRegistrationDate(),
                product.getTarget()
        );
    }

    @Override
    public Product convertToEntity(ProductDto productDTO) {
        return new Product(
                productDTO.getProductId(),
                productDTO.getNutrientId(),
                productDTO.getProductName(),
                productDTO.getProductImage(),
                productDTO.getPrice(),
                productDTO.getStock(),
                productDTO.getProductRegistrationDate(),
                productDTO.getTarget()
        );
    }
}
