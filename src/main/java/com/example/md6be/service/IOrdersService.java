package com.example.md6be.service;

import com.example.md6be.model.Order_detail;
import com.example.md6be.model.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

public interface IOrdersService extends IGeneralService<Orders> {
    List<Orders> findOrdersByCustomerId( Long id);
    Long findNewOrderId();

    List<Orders> findAllOrderByShopId( Long idShop);

    List<Orders> findOrdersByStatusAndCustomer( Long idCustomer);

    void rejectOrder(Long idOrder);

    Orders updateOrderAndQuantityProduct(Orders orders);

    void updateStatusOrder(Long idOrder);

}
