package kr.co.pillgood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kr.co.pillgood.dto.ProductDTO;
import kr.co.pillgood.entity.Product;
import kr.co.pillgood.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    @Override
    public Optional<ProductDTO> updateProduct(int id, ProductDTO updatedProductDTO) {
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
    public ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
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
    public Product convertToEntity(ProductDTO productDTO) {
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

