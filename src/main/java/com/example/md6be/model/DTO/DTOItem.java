package com.example.md6be.model.DTO;

import com.example.md6be.model.Customer;
import com.example.md6be.model.Item;

public class DTOItem {
    private Item item;

    public DTOItem() {
    }

    private Long shop_id;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public DTOItem(Item item, Long shop_id) {
        this.item = item;
        this.shop_id = shop_id;
    }
}
