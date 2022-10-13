package com.example.md6be.repository;

import com.example.md6be.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepo  extends JpaRepository<Voucher, Long> {
//    @Query(value = "select * from voucher where name like ?name ",nativeQuery = true)
    Iterable<Voucher>findAllByCustomer_Id(Long id);

}
