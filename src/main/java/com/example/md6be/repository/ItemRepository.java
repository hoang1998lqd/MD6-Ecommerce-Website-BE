package com.example.md6be.repository;

import com.example.md6be.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select * from item where cart_id = (select cart.customer_id from cart where customer_id = ?1)", nativeQuery = true)
    List<Item> findItemByCustomerId(@Param("id") Long id);


}
