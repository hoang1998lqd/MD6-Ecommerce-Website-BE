package com.example.md6be.repository;

import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.Product;

import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import java.util.List;

@Repository
@EnableSpringConfigured
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "select max(id) from product",
            nativeQuery = true)
    Long findIdNewProduct();


    List<Product> findAllByNameContaining(String name);

    List<Product> findProductByPriceBetween (Double priceMin, Double priceMax);

    List<Product> findAllByCategoryId(long id);

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
