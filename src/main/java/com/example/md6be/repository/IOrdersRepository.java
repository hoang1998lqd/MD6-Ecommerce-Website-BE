package com.example.md6be.repository;

import com.example.md6be.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IOrdersRepository extends JpaRepository<Orders, Long> {
    @Query(value = "select * from orders where customer_id = ?1", nativeQuery = true)
    List<Orders> findOrdersByCustomerId(@Param("id") Long id);

    @Query(value = "select max(id) from orders", nativeQuery = true)
    Long findNewOrderId();

    //Tìm kiếm đơn hàng của Cửa hàng đó
    @Query(value = "select * from orders where shop_id = ?1 and status_exist = 1 ", nativeQuery = true)
    List<Orders> findAllOrderByShopId(Long idShop);



    //Tìm kiếm đơn hàng còn tồn tại theo người dùng
    @Query(value = "select *from orders where status_exist = 1 and customer_id = ?1", nativeQuery = true)
    List<Orders> findOrdersByStatusAndCustomer( Long idCustomer);


}
