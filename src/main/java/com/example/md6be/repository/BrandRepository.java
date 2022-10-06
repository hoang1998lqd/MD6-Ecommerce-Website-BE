package com.example.md6be.repository;

import com.example.md6be.model.Brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Long> {
    @Query(value = "select * from brand where id in (select brand_id from category_brand where category_id = ?1)",
            nativeQuery = true)
    List<Brand> findBrandsByCategoryId(Long id);


}
