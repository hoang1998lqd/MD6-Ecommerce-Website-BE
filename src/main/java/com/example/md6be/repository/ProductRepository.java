package com.example.md6be.repository;

import com.example.md6be.model.Product;

import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@EnableSpringConfigured
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select max(id) from product",
            nativeQuery = true)
    Long findIdNewProduct();
}
