package com.example.md6be.repository;

import com.example.md6be.model.Order_detail;

import com.example.md6be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrder_detailRepository extends JpaRepository<Order_detail, Long> {

    @Query(value = "select * from order_detail where orders_id = ?1", nativeQuery = true)
    List<Order_detail> findAllByOrderId(@Param("id") Long id);


}
