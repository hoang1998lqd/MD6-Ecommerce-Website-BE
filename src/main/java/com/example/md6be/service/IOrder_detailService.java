package com.example.md6be.service;

import com.example.md6be.model.Order_detail;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface IOrder_detailService extends IGeneralService<Order_detail> {

    List<Order_detail> findAllByOrderId(Long idOrder);
    List<Order_detail> saveAll(List<Order_detail> order_details);

    List<Order_detail> findAllOrderDetailByCustomerId(Long idCustomer);

//    List<Order_detail> findAllByOrderId(Long id);

    List<Order_detail> findAllOrderDetailById(Long idShop);


    List<Order_detail> findAllOrderDetailByShopId(Long idShop);
}
