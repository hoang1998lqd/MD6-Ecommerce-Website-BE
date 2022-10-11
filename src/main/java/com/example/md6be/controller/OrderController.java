package com.example.md6be.controller;


import com.example.md6be.model.Order_detail;
import com.example.md6be.model.Orders;
import com.example.md6be.service.ICustomerService;
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

    @Autowired
    ICustomerService customerService;

    @GetMapping
    private ResponseEntity<List<Orders>> findAllOrder() {
        return new ResponseEntity<>(ordersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Orders> findOrderById(@PathVariable Long id) {
        Optional<Orders> orders = ordersService.findById(id);
        if (orders.isPresent()) {
            return new ResponseEntity<>(orders.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/order-detail")
    private ResponseEntity<List<Order_detail>> findAllOrderDetail() {
        return new ResponseEntity<>(detailService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        return new ResponseEntity<>(ordersService.save(order), HttpStatus.CREATED);
    }


    @PostMapping("/order-detail")
    private ResponseEntity<Order_detail> createOrderDetail(@RequestBody Order_detail order_detail) {
        return new ResponseEntity<>(detailService.save(order_detail), HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<Orders> updateOrder(@RequestBody Orders order) {
        Optional<Orders> ordersOptional = ordersService.findById(order.getId());
        if (ordersOptional.isPresent()) {
            return new ResponseEntity<>(ordersService.save(order), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{idCustomer}")
    private ResponseEntity<?> updateOrderStatusExist(@PathVariable Long idCustomer) {
        ordersService.delete(idCustomer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Lấy ID mới nhất vừa tạo của Orders
    @GetMapping("/id-new-order")
    private ResponseEntity<Long> findNewOrderId() {
        return new ResponseEntity<>(ordersService.findNewOrderId(), HttpStatus.OK);
    }

    // Hiển thị danh sách đơn hàng của người đang đăng nhập
    @GetMapping("/id-customer/{id}")
    private ResponseEntity<List<?>> findOrderByCustomerId(@PathVariable Long id) {
        List<Orders> orders = ordersService.findOrdersByCustomerId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    //Tìm kiếm thông tin đơn hàng của cửa hàng đó
    @GetMapping("/shop/{idCustomer}")
    private ResponseEntity<List<Orders>> findAllOrderByShopId(@PathVariable Long idCustomer) {
        return new ResponseEntity<>(ordersService.findAllOrderByShopId(idCustomer), HttpStatus.OK);
    }


}
