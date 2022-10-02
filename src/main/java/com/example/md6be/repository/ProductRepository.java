package com.example.md6be.repository;

import com.example.md6be.model.Product;

import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
@EnableSpringConfigured
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select max(id) from product",
            nativeQuery = true)
    Long findIdNewProduct();

    Optional<Product> findAllByNameContaining(@RequestParam String name);

    @Query(value = "select  * from product between priceMin= ?min and priceMax= ?max", nativeQuery = true)
    Optional<Product> findBetweenPrice (@PathVariable Double priceMin, @PathVariable Double priceMax);
}
