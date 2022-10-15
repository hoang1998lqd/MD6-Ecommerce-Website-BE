package com.example.md6be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Comparator<Orders> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime date_order;

    private LocalDateTime date_ship;

    private Long shop_id;

    @Column(length = 500)
    private String description;

//    Trạng thái tồn tại của đơn hàng
    @Column(nullable = false)
    private int status_exist = 1;

//    Trạng thái vận chuyển hay xác nhận đơn hàng từ NCC hay Người dùng
    @Column(nullable = false)
    private int status_order = 0;

//    Trạng thái thanh toán đơn hàng
    @Column(nullable = false)
    private int status_pay = 0;

    @ManyToOne
    private Customer customer;

    @Override
    public int compare(Orders t, Orders t1) {
        return (int) (t1.getId() - t.getId());
    }
}
