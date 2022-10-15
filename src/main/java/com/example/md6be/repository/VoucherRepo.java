package com.example.md6be.repository;

import com.example.md6be.model.Product;
import com.example.md6be.model.Voucher;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableSpringConfigured
public interface VoucherRepo  extends JpaRepository<Voucher, Long> {
//    @Query(value = "select * from voucher where name like ?name ",nativeQuery = true)
    Iterable<Voucher>findAllByCustomer_Id(Long id);
    @Query(value = "select * from voucher where name like ?2 and customer_id = ?1 ",nativeQuery = true)
    List<Voucher> findAllByNameContaining(Long idCustomer, String name);
}
