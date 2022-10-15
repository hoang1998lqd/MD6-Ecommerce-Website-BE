package com.example.md6be.model.DTO;

public class DTOVoucher {
    int quantity;
    double discount;

    public DTOVoucher() {
    }

    public DTOVoucher(int quantity, double discount) {
        this.quantity = quantity;
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
