package com.example.md6be.service.impl;

import com.example.md6be.model.Voucher;
import com.example.md6be.service.IVoucherService;

import java.util.List;
import java.util.Optional;

public class VoucherService implements IVoucherService {



    @Override
    public List<Voucher> findAll() {
        return null;
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Voucher save(Voucher voucher) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Iterable<Voucher> findAllByCategory_Id(Long id) {
        return null;
    }
}
