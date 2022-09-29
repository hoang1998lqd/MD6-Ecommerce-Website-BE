package com.example.md6be.repository;

import com.example.md6be.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query(value = "select * from cart where customer_id = ?1", nativeQuery = true)
    List<Cart> findCartByCustomerId(@Param("id") Long id);
}
