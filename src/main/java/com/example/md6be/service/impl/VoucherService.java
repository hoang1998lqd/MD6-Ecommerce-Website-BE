package com.example.md6be.service.impl;

import com.example.md6be.model.Voucher;
import com.example.md6be.repository.VoucherRepo;
import com.example.md6be.service.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VoucherService implements IVoucherService {

    @Autowired
    private VoucherRepo voucherRepo;

    @Override
    public Iterable<Voucher> findAllByCustomer_Id(Long id) {
        return voucherRepo.findAllByCustomer_Id(id);
    }

    @Override
    public List<Voucher> findAllByNameContaining(Long idCustomer, String name) {
        return null;
    }

    @Override
    public List<Voucher> findAll() {
        return voucherRepo.findAll();
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return voucherRepo.findById(id);
    }

    @Override
    public Voucher save(Voucher voucher) {
        return voucherRepo.save(voucher);
    }

    @Override
    public void delete(Long id) {
        voucherRepo.deleteById(id);
    }


}
