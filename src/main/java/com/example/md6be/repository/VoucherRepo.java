package com.example.md6be.repository;

import com.example.md6be.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepo  extends JpaRepository<Voucher, Long> {
    Iterable<Voucher>findAllByCategory_Id(Long id);
}
