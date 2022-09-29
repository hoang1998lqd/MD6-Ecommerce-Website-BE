package com.example.md6be.repository;

import com.example.md6be.model.ImageURL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageURLRepository extends JpaRepository<ImageURL, Long> {
    @Query(value = " select imageurl.name from imageurl where product_id = ?1", nativeQuery = true)
    List<String> findImageURLByProductId(@Param("id")Long id);
    @Query(value = "select * from imageurl where id = ?1", nativeQuery = true)
    Optional<ImageURL> findById(@Param("id") Long id);

    @Query(value = "select id from imageurl where product_id = ?1", nativeQuery = true)
    List<Long> findIdByProductId(@Param("id") Long id);

}
