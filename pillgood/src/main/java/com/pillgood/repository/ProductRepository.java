package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{

    List<Product> findByActive(boolean active);
}
