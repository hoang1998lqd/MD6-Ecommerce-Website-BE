package com.example.md6be.service;

import com.example.md6be.model.Orders;


import java.util.List;

public interface IOrdersService extends IGeneralService<Orders> {
    List<Orders> findOrdersByCustomerId(Long id);
    Long findNewOrderId();
}
