package com.example.md6be.service;

import com.example.md6be.model.Product;
import com.example.md6be.model.Voucher;
import com.example.md6be.service.IGeneralService;

import java.util.List;

public interface IVoucherService  extends IGeneralService<Voucher> {
    Iterable<Voucher> findAllByCustomer_Id(Long id);
    List<Voucher> findAllByNameContaining(Long idCustomer, String name);
}
