package com.example.md6be.service.impl;

import com.example.md6be.model.Cart;
import com.example.md6be.model.DTO.DTOOrder;
import com.example.md6be.model.Order_detail;
import com.example.md6be.model.Orders;
import com.example.md6be.model.Product;
import com.example.md6be.repository.IOrdersRepository;
import com.example.md6be.repository.ProductRepository;
import com.example.md6be.service.IOrder_detailService;
import com.example.md6be.service.IOrdersService;
import com.example.md6be.service.IProductService;
import jdk.vm.ci.meta.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService implements IOrdersService {

    @Autowired
    IOrdersRepository ordersRepository;

    @Autowired
    IProductService iProductService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    IOrder_detailService iOrder_detailService;


    @Override
    public List<Orders> findAll() {
        List<Orders> orders = ordersRepository.findAll();
        List<Orders> ordersList = new ArrayList<>();
        for (Orders order : orders) {
            if (order.getStatus_exist() != 0) {
                ordersList.add(order);
            }
        }
        return ordersList;
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public Orders save(Orders orders) {
          LocalDateTime date_order = LocalDateTime.now();
          orders.setDate_order(date_order);
          ordersRepository.save(orders);
        return orders;
    }


    //Truyền vào Id của Customer
    @Override
    public void delete(Long id) {
        List<Orders> orders = findOrdersByCustomerId(id);
        for (Orders order : orders) {
            order.setStatus_exist(0);
            save(order);
        }
    }

    @Override
    public List<Orders> findOrdersByCustomerId(Long id) {
        return ordersRepository.findOrdersByCustomerId(id);
    }

    @Override
    public Long findNewOrderId() {
        return ordersRepository.findNewOrderId();
    }

    @Override


    // List Order của Cửa hàng

    public List<Orders> findAllOrderByShopId(Long idCustomer) {
        List<Orders> orders = ordersRepository.findAllOrderByShopId(idCustomer);
        return sortOrder(orders);

    }

    // List Order của CỬA HÀNG (DTOORDER)
    @Override
    public List<DTOOrder> findAllDTOOrderByShopId(Long idShop) {
        List<DTOOrder> dtoOrderList = new ArrayList<>();
        List<Orders> ordersList = findAllOrderByShopId(idShop);
        for (Orders orders : ordersList){
             String status_order = null;
             String status_pay;
             String customer;
             if (orders.getStatus_order() == 0){
                 status_order = "Chờ xác nhận";
             }else if (orders.getStatus_order() == 1){
                 status_order = "Đã gửi hàng";
             }else {
                 status_order = "Đã nhận được hàng";
             }
             if (orders.getStatus_pay() == 0){
                 status_pay = "Chưa thanh toán";
             }else {
                 status_pay = "Đã thanh toán";
             }
             customer = orders.getCustomer().getName();
             DTOOrder dtoOrder = new DTOOrder(orders.getId(), orders.getDate_order(), orders.getDate_ship(),orders.getDescription(),
                     status_order,status_pay,customer
             );
             dtoOrderList.add(dtoOrder);

        }
        return dtoOrderList;
    }


    // List Order của NGƯỜI DÙNG (DTOORDER)
    public List<DTOOrder> findAllDTOOrderByCustomerId(Long idCustomer) {
        List<DTOOrder> dtoOrderList = new ArrayList<>();
        List<Orders> ordersList = findOrdersByStatusAndCustomer(idCustomer);
        for (Orders orders : ordersList){
             String status_order = null;
             String status_pay;
             String customer;
             if (orders.getStatus_order() == 0){
                 status_order = "Chờ xác nhận";
             }else if (orders.getStatus_order() == 1){
                 status_order = "Đã gửi hàng";
             }else {
                 status_order = "Đã nhận được hàng";
             }
             if (orders.getStatus_pay() == 0){
                 status_pay = "Chưa thanh toán";
             }else {
                 status_pay = "Đã thanh toán";
             }
             customer = orders.getCustomer().getName();
             DTOOrder dtoOrder = new DTOOrder(orders.getId(), orders.getDate_order(), orders.getDate_ship(),orders.getDescription(),
                     status_order,status_pay,customer
             );
             dtoOrderList.add(dtoOrder);

        }
        return dtoOrderList;
    }

    // List Order của NGƯỜI DÙNG

    @Override
    public List<Orders> findOrdersByStatusAndCustomer(Long idCustomer) {
        List<Orders> orders = ordersRepository.findOrdersByStatusAndCustomer(idCustomer);

        return sortOrder(orders);
    }

    private List<Orders> sortOrder(List<Orders> orders) {
        orders.sort(new Comparator<Orders>() {
            @Override
            public int compare(Orders orders, Orders t1) {
                return (int) (t1.getId() - orders.getId());
            }
        });
        orders.sort(new Comparator<Orders>() {
            @Override
            public int compare(Orders orders, Orders t1) {
                return orders.getStatus_order() - t1.getStatus_order();
            }
        });
        return orders;
    }

    public void rejectOrder(Long idOrder) {
        Orders orders = ordersRepository.rejectOrder(idOrder);
        orders.setStatus_exist(0);
        save(orders);
    }


    // Cập nhật số lượng khi sản phẩm được CỬA HÀNG ĐÓ XÁC NHẬN SẼ GỬI HÀNG
    @Override
    public Orders updateOrderAndQuantityProduct(Orders orders) {
        Optional<Orders> ordersOptional = findById(orders.getId());
        if (ordersOptional.isPresent()) {
            LocalDateTime date_ship = LocalDateTime.now();
            orders.setDate_ship(date_ship);
            ordersRepository.save(orders);
            List<Order_detail> order_details = iOrder_detailService.findAllByOrderId(ordersOptional.get().getId());
            for (Order_detail order_detail : order_details) {
                Optional<Product> productOptional = iProductService.findById(order_detail.getProduct().getId());
                if (productOptional.isPresent()) {
                    Product product = productOptional.get();
                    int newQuantity = product.getAmount() - order_detail.getQuantity();
                    product.setAmount(newQuantity);
                    iProductService.save(product);
                }
            }
        }
        return orders;
    }


    private Orders getOrders(Orders orders) {
        Optional<Orders> ordersOptional = findById(orders.getId());
        if (ordersOptional.isPresent()) {
            List<Order_detail> order_details = iOrder_detailService.findAllByOrderId(ordersOptional.get().getId());
            for (Order_detail order_detail : order_details) {
                Optional<Product> productOptional = iProductService.findById(order_detail.getProduct().getId());
                if (productOptional.isPresent()) {
                    Product product = productOptional.get();
                    int newQuantity = product.getAmount() - order_detail.getQuantity();
                    product.setAmount(newQuantity);
                    iProductService.save(product);
                }
            }
        }
        return null;
    }


    @Override
    public Orders updateOrderProduct(Orders orders) {
        return getOrders(orders);
    }

    @Override
    public Orders saveOrder(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public void updateStatusOrder(Long idOrder) {
        Orders orders = ordersRepository.rejectOrder(idOrder);
        orders.setStatus_order(1);
        save(orders);

    }

}
