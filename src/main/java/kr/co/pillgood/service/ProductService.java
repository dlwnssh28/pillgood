package kr.co.pillgood.service;

import kr.co.pillgood.dto.ProductDTO;
import kr.co.pillgood.entity.Product;
import kr.co.pillgood.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO createProduct(ProductDTO productDTO);
    Optional<ProductDTO> updateProduct(int id, ProductDTO updatedProductDTO);
    boolean deleteProduct(int id);
    ProductDTO convertToDTO(Product product);
    Product convertToEntity(ProductDTO productDTO);
}
