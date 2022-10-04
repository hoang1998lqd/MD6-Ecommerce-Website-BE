package com.example.md6be.service;

import com.example.md6be.model.Voucher;
import com.example.md6be.service.IGeneralService;

public interface IVoucherService  extends IGeneralService<Voucher> {
    Iterable<Voucher> findAllByCustomer_Id(Long id);
}
