package com.example.md6be.model.DTO;

import java.time.LocalDateTime;

public class DTOOrder {
    private Long id;
    private LocalDateTime date_order;
    private LocalDateTime date_ship;
    private String description;
    private String status_order;
    private String status_pay;
    private String customer;

    public DTOOrder() {
    }

    public DTOOrder(Long id, LocalDateTime date_order, LocalDateTime date_ship, String description, String status_order, String status_pay, String customer) {
        this.id = id;
        this.date_order = date_order;
        this.date_ship = date_ship;
        this.description = description;
        this.status_order = status_order;
        this.status_pay = status_pay;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate_order() {
        return date_order;
    }

    public void setDate_order(LocalDateTime date_order) {
        this.date_order = date_order;
    }

    public LocalDateTime getDate_ship() {
        return date_ship;
    }

    public void setDate_ship(LocalDateTime date_ship) {
        this.date_ship = date_ship;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus_order() {
        return status_order;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
    }

    public String getStatus_pay() {
        return status_pay;
    }

    public void setStatus_pay(String status_pay) {
        this.status_pay = status_pay;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
