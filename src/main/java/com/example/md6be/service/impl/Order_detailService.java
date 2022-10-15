package com.example.md6be.service.impl;

import com.example.md6be.model.Order_detail;
import com.example.md6be.repository.IOrder_detailRepository;
import com.example.md6be.repository.IOrdersRepository;
import com.example.md6be.service.IOrder_detailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Order_detailService implements IOrder_detailService {
    @Autowired
    IOrder_detailRepository detailRepository;


    @Override
    public List<Order_detail> findAll() {
        return detailRepository.findAll();
    }

    @Override
    public Optional<Order_detail> findById(Long id) {
        return detailRepository.findById(id);
    }

    @Override
    public Order_detail save(Order_detail order_detail) {
        return detailRepository.save(order_detail);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Order_detail> findAllByOrderId(Long idOrder) {
        return detailRepository.findAllByOrderId(idOrder);
    }

    @Override
    public List<Order_detail> saveAll(List<Order_detail> order_details) {
        return detailRepository.saveAll(order_details);
    }

    @Override
    public List<Order_detail> findAllOrderDetailByCustomerId(Long idCustomer) {
        return detailRepository.findAllOrderDetailByCustomerId(idCustomer);
    }

    @Override
    public List<Order_detail> findAllOrderDetailById(Long idShop) {
        return detailRepository.findAllOrderDetailById(idShop);
    }

    @Override
    public List<Order_detail> findAllOrderDetailByShopId(Long idShop) {
        return detailRepository.findAllOrderDetailByShopId(idShop);
    }
}
