package kr.co.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.pillgood.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
