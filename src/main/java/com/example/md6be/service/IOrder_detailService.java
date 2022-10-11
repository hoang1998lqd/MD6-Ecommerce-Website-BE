package com.example.md6be.service;

import com.example.md6be.model.Order_detail;


import java.util.List;

public interface IOrder_detailService extends IGeneralService<Order_detail> {
    List<Order_detail> findAllByOrderId(Long idOrder);


}
