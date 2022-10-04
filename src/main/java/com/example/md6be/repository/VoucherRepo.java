package com.example.md6be.repository;

import com.example.md6be.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepo  extends JpaRepository<Voucher, Long> {
    Iterable<Voucher>findAllByCustomer_Id(Long id);
    Optional<Voucher> findByCustomer_Id(Long id);
}
