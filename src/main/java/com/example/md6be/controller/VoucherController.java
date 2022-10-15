package com.example.md6be.controller;

import com.example.md6be.model.Customer;
import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.DTO.DTOVoucher;
import com.example.md6be.model.Product;
import com.example.md6be.model.Voucher;
import com.example.md6be.repository.VoucherRepo;
import com.example.md6be.service.IVoucherService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/voucher")

public class VoucherController {
    @Autowired
    private IVoucherService voucherService;

    @GetMapping
    public ResponseEntity<List<Voucher>> findAll() {
        return new ResponseEntity<>(voucherService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/find-name-vouchers/{name}/{idCustomer}")
    private ResponseEntity<List<Voucher>> findAllByNameContaining(@PathVariable("name") String name, @PathVariable("idCustomer") Long idCustomer) {
        return new ResponseEntity<>(voucherService.findAllByNameContaining(idCustomer, name), HttpStatus.OK);
    }
    @GetMapping("/find-voucher/{idCustomer}")
    public ResponseEntity<?> findAllByStore_Id(@PathVariable("idCustomer") Long idCustomer) {
        return new ResponseEntity<>(voucherService.findAllByCustomer_Id(idCustomer), HttpStatus.OK);
    }

    @PostMapping("/create-voucher")
    private ResponseEntity<List<Voucher>> createVoucher(@RequestBody DTOVoucher data) {
        RandomStringUtils random = new RandomStringUtils();
        ArrayList<Voucher> vouchers = new ArrayList<>();
        System.out.println(data.getQuantity());
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        System.out.println(userDetails.getUsername());

        for (int i = 0; i < data.getQuantity(); i++) {
            String name = random.randomAlphanumeric(12);
            Voucher voucher = new Voucher();
            voucher.setName(name);
            voucher.setDiscount(data.getDiscount());
            voucher.getCustomer();
            // quantity temp
            voucher.setQuantity(10l);
            vouchers.add(voucher);
            System.out.println(voucher.getName());
        }
        return new ResponseEntity<>(voucherService.saveAll(vouchers), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-voucher/{id}")
    public ResponseEntity<?> deleteVoucher(@PathVariable("id") Long id) {
        Optional<Voucher> voucherOptional = voucherService.findById(id);
        if (!voucherOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            voucherService.delete(id);
            return new ResponseEntity<>(voucherOptional.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/update-voucher")
    private ResponseEntity<?> updateVoucher(@RequestBody Voucher voucher) {
        Optional<Voucher> voucherOptional = voucherService.findById(voucher.getId());
        if (voucherOptional.isPresent()) {
            return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
