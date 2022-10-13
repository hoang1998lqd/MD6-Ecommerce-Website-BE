package com.example.md6be.repository;

import com.example.md6be.model.Order_detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrder_detailRepository extends JpaRepository<Order_detail, Long> {

    @Query(value = "select * from order_detail where orders_id = ?1", nativeQuery = true)

    List<Order_detail> findAllByOrderId(@Param("idOrder") Long idOrder);

    @Query(value = "select * from order_detail where orders_id in ( select id from orders where customer_id = ?1)",nativeQuery = true)
    List<Order_detail> findAllOrderDetailByCustomerId(@Param("idCustomer") Long idCustomer);

//    List<Order_detail> findAllByOrderId(@Param("id") Long id);


    // Tìm kiếm toàn bộ Order Detail của Cửa hàng đó
    @Query(value = "select * from order_detail where orders_id in (select id from orders where shop_id = ?1 and status_exist = 1)", nativeQuery = true)
    List<Order_detail> findAllOrderDetailById(Long idShop);


    // Tìm kiếm toàn bộ Order Detail của NGƯỜI BÁN đó
    @Query(value = "select * from order_detail where orders_id in (select id from orders where shop_id = ?1 " +
            "and status_exist = 1)", nativeQuery = true)
    List<Order_detail> findAllOrderDetailByShopId(Long idShop);

}
