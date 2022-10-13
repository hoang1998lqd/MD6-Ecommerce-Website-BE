package com.example.md6be.controller;


import com.example.md6be.model.Order_detail;
import com.example.md6be.model.Orders;
import com.example.md6be.service.IOrder_detailService;
import com.example.md6be.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    IOrder_detailService detailService;

    @Autowired
    IOrdersService ordersService;

    @GetMapping
    private ResponseEntity<List<Orders>> findAllOrder() {
        return new ResponseEntity<>(ordersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/order-detail")
    private ResponseEntity<List<Order_detail>> findAllOrderDetail() {
        return new ResponseEntity<>(detailService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Orders> createOrder(@RequestBody Orders order){
        return new ResponseEntity<>(ordersService.save(order), HttpStatus.CREATED);
    }


    @PostMapping("/order-detail")
    private ResponseEntity<List<Order_detail>> createOrderDetail(@RequestBody List<Order_detail> order_details){
        return new ResponseEntity<>(detailService.saveAll(order_details), HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<Orders> updateOrder(@RequestBody Orders order){
        Optional<Orders> ordersOptional = ordersService.findById(order.getId());
        if(ordersOptional.isPresent()){
            return new ResponseEntity<>(ordersService.save(order),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{idOrder}")
    private ResponseEntity<?> updateOrderStatusExist(@PathVariable Long idOrder){
        ordersService.rejectOrder(idOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Lấy ID mới nhất vừa tạo của Orders
    @GetMapping("/id-new-order")
    private ResponseEntity<Long> findNewOrderId() {
        return new ResponseEntity<>(ordersService.findNewOrderId(), HttpStatus.OK);
    }

    //Tìm kiếm thông tin đơn hàng của cửa hàng đó
    @GetMapping("/shop/{idShop}")
    private ResponseEntity<List<Orders>> findAllOrderByShopId(@PathVariable Long idShop){
        return  new ResponseEntity<>(ordersService.findAllOrderByShopId(idShop),HttpStatus.OK);
    }

    // tìm kiếm đơn hàng còn tồn taị theo id người dùng
    @GetMapping("/order-customer/{idCustomer}")
    private ResponseEntity<List<Orders>> findAllOrderByOrderId(@PathVariable Long idCustomer){
        return new ResponseEntity<>(ordersService.findOrdersByStatusAndCustomer(idCustomer), HttpStatus.OK);
    }


    // tìm kiếm tất cả order-detail theo 1 order
    @GetMapping("/order-detail-order/{idOrder}")
    private ResponseEntity<List<Order_detail>> findAllOrderDetailByOrder(@PathVariable Long idOrder){
        return  new ResponseEntity<>(detailService.findAllByOrderId(idOrder), HttpStatus.OK);
    }

    // tìm kiếm tất cả order-detail theo idCustomer
    @GetMapping("/order-detail-by-idCustomer/{idCustomer}")
    private ResponseEntity<List<Order_detail>> findAllOrderDetailByCustomerId(@PathVariable Long idCustomer){
        return new ResponseEntity<>(detailService.findAllOrderDetailByCustomerId(idCustomer),HttpStatus.OK);
    }

}
