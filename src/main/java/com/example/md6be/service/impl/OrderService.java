package com.example.md6be.service.impl;

<<<<<<< HEAD
import com.example.md6be.model.Cart;
=======
import com.example.md6be.model.Order_detail;
>>>>>>> 0a4c9c45d9fee0cf6f2e637f0009f94b414d394e
import com.example.md6be.model.Orders;
import com.example.md6be.model.Product;
import com.example.md6be.repository.IOrdersRepository;
import com.example.md6be.repository.ProductRepository;
import com.example.md6be.service.IOrder_detailService;
import com.example.md6be.service.IOrdersService;
import com.example.md6be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for (Orders order : orders){
            if (order.getStatus_exist() != 0){
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
        LocalDateTime date_ship = date_order.plusDays(2);
        orders.setDate_order(date_order);
        orders.setDate_ship(date_ship);
        return ordersRepository.save(orders);
    }


    //Truyền vào Id của Customer
    @Override
    public void delete(Long id) {
        List<Orders> orders = findOrdersByCustomerId(id);
        for (Orders order : orders){
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
    public List<Orders> findAllOrderByShopId(Long idCustomer) {
        List<Orders> orders = ordersRepository.findAllOrderByShopId(idCustomer);
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

    @Override
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
    public void updateStatusOrder(Long idOrder) {
        Orders orders = ordersRepository.rejectOrder(idOrder);
        orders.setStatus_order(1);
        save(orders);

    }

}
