package com.pillgood.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pillgood.dto.CartDto;
import com.pillgood.entity.Cart;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByMemberUniqueId(String memberUniqueId);
	
}	