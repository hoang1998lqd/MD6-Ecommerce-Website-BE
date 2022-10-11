package com.example.md6be.service;

import com.example.md6be.model.Orders;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface IOrdersService extends IGeneralService<Orders> {
    List<Orders> findOrdersByCustomerId( Long id);
    Long findNewOrderId();

    List<Orders> findAllOrderByShopId( Long idCustomer);

    void rejectOrder(Long idOrder);
}
