package com.pillgood.repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
=======
import org.springframework.data.jpa.repository.JpaRepository; 
>>>>>>> origin/hzz
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pillgood.dto.CartDto;
import com.pillgood.entity.Cart;

<<<<<<< HEAD
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByMemberUniqueId(String memberUniqueId);
	
}	
=======
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT new com.pillgood.dto.CartDto(c.cartNo, c.memberUniqueId, c.productId, c.productQuantity, p.price) " +
           "FROM Cart c JOIN Product p ON c.productId = p.productId " +
           "WHERE c.memberUniqueId = :memberUniqueId")
    List<CartDto> findCartsByMemberUniqueIdWithPrice(String memberUniqueId);
}
>>>>>>> origin/hzz
