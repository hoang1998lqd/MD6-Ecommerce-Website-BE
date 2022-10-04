package com.example.md6be.repository;

import com.example.md6be.model.Product;

import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableSpringConfigured
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select max(id) from product",
            nativeQuery = true)
    Long findIdNewProduct();

    @Query(value = "select * from product where customer_id = ?1 and status = 1",
            nativeQuery = true)
    List<Product> findProductByCustomerId(Long id);
    @Query(value = "select * from product where status = 1",
            nativeQuery = true)
    List<Product> findAllProduct();
    @Query(value = "select * from product where not customer_id = ?1 and status = 1",
            nativeQuery = true)
    List<Product> findAllProduct(Long idCustomer);




}
