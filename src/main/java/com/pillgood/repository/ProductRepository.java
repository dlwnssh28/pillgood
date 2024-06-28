package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
