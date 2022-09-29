package com.example.md6be.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime date_order;

    private LocalDateTime date_ship;

    @Column(length = 500)
    private String description;

//    Trạng thái tồn tại của đơn hàng

    @Column(nullable = false)
    private int status_exist;

//    Trạng thái vận chuyển hay xác nhận đơn hàng từ NCC hay Người dùng
    @Column(nullable = false)
    private int status_order;

//    Trạng thái thanh toán đơn hàng
    @Column(nullable = false)
    private int status_pay;

    @ManyToOne
    private Customer customer;
}
