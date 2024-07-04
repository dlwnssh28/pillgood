package com.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pillgood.entity.Nutrient;
import com.pillgood.repository.NutrientRepository;
import org.springframework.stereotype.Service;

import com.pillgood.dto.ProductDto;
import com.pillgood.entity.Product;
import com.pillgood.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final NutrientRepository nutrientRepository;

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
        // dto -> entity 변환
        Product product = convertToEntity(productDTO);
        // nutrient 엔티티 병합
        Nutrient nutrient = nutrientRepository.findById(product.getNutrient().getNutrientId())
                .orElseThrow(() -> new RuntimeException("Nutrient를 찾을 수 없음"));
        // product 엔티티 저장
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    @Override
    public Optional<ProductDto> updateProduct(int id, ProductDto updatedProductDTO) {
        return productRepository.findById(id)
                .map(product -> {
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
                product.getNutrient().getNutrientId(),
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
        Product product = new Product();
        product.setProductId(productDTO.getProductId());

        // nutrientId로 Nutrient 객체 설정
        Nutrient nutrient = new Nutrient();
        nutrient.setNutrientId(productDTO.getNutrientId());
        product.setNutrient(nutrient);

        product.setProductName(productDTO.getProductName());
        product.setProductImage(productDTO.getProductImage());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setProductRegistrationDate(productDTO.getProductRegistrationDate());
        product.setTarget(productDTO.getTarget());

        return product;
    }


}
