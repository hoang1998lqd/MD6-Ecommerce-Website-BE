package com.example.md6be.controller;

import com.example.md6be.model.Product;
import com.example.md6be.model.Voucher;
import com.example.md6be.service.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/voucher")

public class VoucherController {
    @Autowired
    private IVoucherService voucherService;
    @GetMapping("/find-voucher/{CustomerId}")
    public ResponseEntity<?> findAllByStore_Id(@PathVariable("CustomerId") Long CustomerId) {
        Iterable<Voucher> vouchers = voucherService.findAllByCustomer_Id(CustomerId);
        return new ResponseEntity<>(vouchers, HttpStatus.OK);
    }
//    @PostMapping
//    public ResponseEntity<?> createVoucher(@RequestBody Voucher voucher) {
//        return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.CREATED);
//    }
    @PostMapping
    private ResponseEntity<Voucher> createVoucher(@RequestBody Voucher voucher) {
        return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-voucher/{id}")
    public ResponseEntity<?> deleteVoucher(@PathVariable("id") Long id) {
        Optional<Voucher> voucherOptional = voucherService.findById(id);
        if (!voucherOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            voucherService.delete(id);
            return new ResponseEntity<>(voucherOptional.get(), HttpStatus.OK);
        }
    }
    @PutMapping("/update-voucher")
    private ResponseEntity<?> updateVoucher(@RequestBody Voucher voucher){
        Optional<Voucher> voucherOptional = voucherService.findById(voucher.getId());
        if(voucherOptional.isPresent()){
            return new ResponseEntity<>(voucherService.save(voucher),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
