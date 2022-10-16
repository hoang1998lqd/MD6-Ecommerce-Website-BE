package com.example.md6be.repository;

import com.example.md6be.model.Order_detail;
import com.example.md6be.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional

public interface IOrdersRepository extends JpaRepository<Orders, Long> {
    @Query(value = "select * from orders where customer_id = ?1", nativeQuery = true)
    List<Orders> findOrdersByCustomerId(@Param("id") Long id);

    @Query(value = "select max(id) from orders", nativeQuery = true)
    Long findNewOrderId();

    //Tìm kiếm đơn hàng của Cửa hàng đó
    @Query(value = "select * from orders where shop_id = ?1 and status_exist = 1 ", nativeQuery = true)
    List<Orders> findAllOrderByShopId(Long idShop);


    //Tìm kiếm tất cả đơn hàng tồn tại theo người dùng
    @Query(value = "select * from orders where status_exist = 1 and customer_id = ?1", nativeQuery = true)
    List<Orders> findOrdersByStatusAndCustomer( Long idCustomer);


    @Query(value = "select * from orders where status_exist = 1 and id = ?1", nativeQuery = true)
    Orders rejectOrder(Long idOrder);




}
